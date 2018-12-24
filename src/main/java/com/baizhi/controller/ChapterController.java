package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;


    @RequestMapping("/insertOne")
    public void insertOne(Chapter c, HttpSession session, MultipartFile file1) {
        System.out.println(c + "------");
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/image/mp3");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String oldName = file1.getOriginalFilename();
        //获取文件后缀
        String extension = FilenameUtils.getExtension(oldName);
        String newName = UUID.randomUUID().toString();
        //用UUID表示唯一的专辑
        newName = newName + "." + extension;
        // 上传
        try {
            file1.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String size = null;
        Long a = file1.getSize();
        if (a < 1024) {
            size = a + "BT";
        } else if (a < 1048576) {
            size = a / 1024 + "KB";
        } else if (a < 1073741824) {
            size = a / 1024 / 1024 + "MB";
        } else {
            size = a / 1024 / 1024 / 1024 + "GB";
        }
        c.setSize(size);
        c.setUrl("/image/mp3/" + newName);

        chapterService.insetOne(c);
    }

    @RequestMapping("/download")
    public void download(String url, String title, HttpSession session, HttpServletResponse response) {

        String realPath = session.getServletContext().getRealPath(url);
        File f = new File(realPath);
        String extension = FilenameUtils.getExtension(url);
        String oldName = title + "." + extension;
        byte[] bs = new byte[0];
        try {
            bs = FileUtils.readFileToByteArray(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.setHeader("content-disposition", "attchment;filename=" + URLEncoder.encode(oldName, "utf-8"));
            //响应返回值类型
            response.setContentType("audio/mpeg");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

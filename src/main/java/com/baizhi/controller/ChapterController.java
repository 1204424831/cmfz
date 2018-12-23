package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/insertOne")
    public void insertOne(Chapter c, HttpSession session, MultipartFile file1) throws IOException {
        System.out.println(c + "------");
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/image/mp3");
        // 目标文件
        File descFile = new File(realPath + "/" + file1.getOriginalFilename());
        // 上传
        file1.transferTo(descFile);
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
        c.setUrl("/image/mp3/" + file1.getOriginalFilename());
        chapterService.insetOne(c);
    }

    @RequestMapping("/download")
    public void download(String id, HttpSession session, HttpServletResponse response) throws IOException {
        Chapter c = chapterService.getOne(id);
        String realPath = session.getServletContext().getRealPath(c.getUrl());
        System.out.println(c.getUrl() + "-------------");
        File f = new File(realPath);
        byte[] bs = FileCopyUtils.copyToByteArray(f);
        String[] name = c.getUrl().split("/");
        String a = name[3];
        System.out.println(a);
        response.setHeader("content-disposition", "attchment;filename=" + URLEncoder.encode(a, "utf-8"));
        ServletOutputStream out = response.getOutputStream();
        out.write(bs);
    }

}

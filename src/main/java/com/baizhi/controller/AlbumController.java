package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumMessage;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/querySome")
    public AlbumMessage querySome(Integer rows, Integer page) {

        AlbumMessage am = albumService.querySome(rows, page);

        return am;
    }

    @RequestMapping("/queryOne")
    public Album queryOne(Integer id) {
        Album a = albumService.queryOne(id);
        return a;
    }

    @RequestMapping("/insertOne")
    public void insertOne(Album a, HttpSession session, MultipartFile file1) throws IOException {
        System.out.println(a);
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/image");
        // 目标文件
        File descFile = new File(realPath + "/" + file1.getOriginalFilename());
        // 上传
        file1.transferTo(descFile);
        a.setCoverImg("/image/" + file1.getOriginalFilename());
        albumService.insertOne(a);
    }
}

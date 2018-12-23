package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerMessage;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/querySome")
    public BannerMessage querySome(Integer rows, Integer page) {
        BannerMessage bm = bannerService.querySome(rows, page);
        return bm;
    }

    @RequestMapping("/insertOne")
    public String insertAll(Banner b, HttpSession session, MultipartFile file1) throws IllegalStateException, IOException {
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/img");
        // 目标文件
        File descFile = new File(realPath + "/" + file1.getOriginalFilename());
        // 上传
        file1.transferTo(descFile);
        b.setImgPath("/img/" + file1.getOriginalFilename());
        bannerService.insertOne(b);
        return "ok";
    }

    @RequestMapping("/updateOne")
    public String updateOne(Banner b) {
        System.out.println(b);
        bannerService.updateOne(b);
        return "ok";
    }

    @RequestMapping("/deleteOne")
    public String deleteOne(Integer id, HttpSession session) {
        System.out.println(id);
        Banner b = bannerService.queryOne(id);
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath(b.getImgPath());
        File f = new File(realPath);
        f.delete();
        bannerService.deleteOne(id);
        return "ok";
    }
}

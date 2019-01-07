package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.entity.ArticleMessage;
import com.baizhi.service.ArticleService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/querySome")
    public ArticleMessage querySome(Integer rows, Integer page) {
        ArticleMessage a = articleService.querySomeArticle(rows, page);
        return a;
    }

    @RequestMapping("/insertOne")
    public void insertOne(Article a, MultipartFile file) {
        System.out.println(a);
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        a.setInsertImg(storePath.getFullPath());
        articleService.insertOne(a);
    }

    @RequestMapping("/queryArticle")
    public List<Article> queryArticle(String param) {
        System.out.println(param);
        List<Article> list = articleService.queryArticle(param);
        for (Article a : list) {
            System.out.println(a);
        }
        return list;
    }
}

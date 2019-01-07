package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.ArticleMessage;
import com.baizhi.entity.StuMess;

import java.util.List;

public interface ArticleService {
    public List<StuMess> querySomeStu(Integer gid);

    public List<StuMess> queryAllStu(Integer gid);

    public List<StuMess> queryOtherStu(Integer gid);

    public ArticleMessage querySomeArticle(Integer rows, Integer page);

    public void insertOne(Article a);

    public List<Article> queryArticle(String param);
}

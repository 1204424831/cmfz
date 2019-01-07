package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.ArticleMessage;
import com.baizhi.entity.Gurn;
import com.baizhi.entity.StuMess;
import com.baizhi.mapper.ArticleDao;
import com.baizhi.mapper.GurnDao;
import com.baizhi.mapper.LuceneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private LuceneDao luceneDao;
    @Autowired
    private GurnDao gurnDao;

    @Override
    public List<StuMess> querySomeStu(Integer gid) {
        List<StuMess> list = articleDao.querySomeArti(gid);
        for (StuMess stuMess : list) {
            stuMess.setType(1);
        }
        return list;
    }

    @Override
    public List<StuMess> queryAllStu(Integer gid) {
        List<StuMess> list = articleDao.queryAllArti(gid);
        for (StuMess stuMess : list) {
            stuMess.setType(1);
        }
        return list;
    }

    @Override
    public List<StuMess> queryOtherStu(Integer gid) {
        List<StuMess> list = articleDao.queryOtherArti(gid);
        for (StuMess stuMess : list) {
            stuMess.setType(1);
        }
        return list;
    }

    @Override
    public ArticleMessage querySomeArticle(Integer rows, Integer page) {
        List<Article> list = articleDao.querySomeArticle(rows, page);
        Article a = new Article();
        Integer tatol = articleDao.selectCount(a);
        ArticleMessage ae = new ArticleMessage(list, tatol);
        return ae;
    }

    @Override
    public void insertOne(Article a) {
        articleDao.insertOneArti(a);
        Gurn g = gurnDao.selectByPrimaryKey(a.getG().getId());
        System.out.println(g + "------------");
        a.setG(g);
        System.out.println(a);
        luceneDao.addArticeLuce(a);
    }

    @Override
    public List<Article> queryArticle(String param) {
        List<Article> list = luceneDao.queryAllArt(param);
        return list;
    }
}

package com.baizhi.mapper;

import com.baizhi.entity.Article;
import com.baizhi.entity.StuMess;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDao extends Mapper<Article> {
    public List<StuMess> queryAllArti(Integer gid);

    public List<StuMess> querySomeArti(Integer gid);

    public List<StuMess> queryOtherArti(Integer gid);

    public List<Article> querySomeArticle(@Param("rows") Integer rows, @Param("page") Integer page);

    public void insertOneArti(Article a);
}

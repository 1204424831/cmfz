package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.Chapter1;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterDao extends Mapper<Chapter> {
    public List<Chapter1> queryAllCha(Integer aid);
}

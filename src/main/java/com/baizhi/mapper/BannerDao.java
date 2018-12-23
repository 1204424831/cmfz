package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public List<Banner> querySome(@Param("rows") Integer rows, @Param("page") Integer page);

    public void deleteOne(Integer id);

    public void updateOne(Banner b);

    public Banner queryOne(Integer id);

    public void insertOne(Banner b);

    public Integer getCount();
}

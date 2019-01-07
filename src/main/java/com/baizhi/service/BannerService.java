package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Banner1;
import com.baizhi.entity.BannerMessage;

import java.util.List;

public interface BannerService {
    public BannerMessage querySome(Integer rows, Integer page);

    public void deleteOne(Integer id);

    public void updateOne(Banner b);

    public void insertOne(Banner b);

    public Banner queryOne(Integer id);

    public List<Banner1> queryAll();
}

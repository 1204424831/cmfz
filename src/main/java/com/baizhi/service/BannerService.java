package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerMessage;

public interface BannerService {
    public BannerMessage querySome(Integer rows, Integer page);

    public void deleteOne(Integer id);

    public void updateOne(Banner b);

    public void insertOne(Banner b);

    public Banner queryOne(Integer id);
}

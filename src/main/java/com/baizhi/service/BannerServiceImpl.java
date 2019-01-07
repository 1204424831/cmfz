package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Banner1;
import com.baizhi.entity.BannerMessage;
import com.baizhi.mapper.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public BannerMessage querySome(Integer rows, Integer page) {
        List<Banner> list = bannerDao.querySome(rows, page);
        Integer total = bannerDao.getCount();
        BannerMessage bm = new BannerMessage(total, list);
        return bm;
    }

    @Override
    public void deleteOne(Integer id) {
        bannerDao.deleteOne(id);
    }

    @Override
    public void updateOne(Banner b) {
        bannerDao.updateOne(b);
    }

    @Override
    public void insertOne(Banner b) {
        bannerDao.insertOne(b);
    }

    @Override
    public Banner queryOne(Integer id) {
        return bannerDao.queryOne(id);
    }

    @Override
    public List<Banner1> queryAll() {
        List<Banner1> list = bannerDao.queryAll();
        return list;
    }
}

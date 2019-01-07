package com.baizhi.service;

import com.baizhi.entity.*;
import com.baizhi.mapper.AlbumDao;
import com.baizhi.mapper.ChapterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;
    @Override
    public AlbumMessage querySome(Integer rows, Integer page) {
        List<Album> list = albumDao.querySomAlbum(rows, page);
        Integer total = albumDao.selectCount(new Album());
        AlbumMessage am = new AlbumMessage(list, total);
        return am;
    }

    public Album queryOne(Integer id) {
        Album a = albumDao.selectByPrimaryKey(id);
        return a;
    }

    @Override
    public void insertOne(Album a) {
        albumDao.insert(a);
    }

    public List<Album> queryAll() {
        return albumDao.queryAllAlbum();
    }

    @Override
    public List<StuMess> queryAllStu() {
        List<StuMess> list = albumDao.queryAllAlb();
        for (StuMess stuMess : list) {
            stuMess.setType(0);
        }
        return list;
    }

    @Override
    public List<StuMess> querySomeStu() {
        List<StuMess> list = albumDao.querySomeAlb();
        for (StuMess stuMess : list) {
            stuMess.setType(0);
        }
        return list;
    }

    @Override
    public Album1 queryOneAlu(Integer id) {
        Album1 a = albumDao.queryOneAlb(id);
        return a;
    }

    @Override
    public List<Chapter1> queryAllCha(Integer aid) {
        List<Chapter1> list = chapterDao.queryAllCha(aid);
        return list;
    }
}

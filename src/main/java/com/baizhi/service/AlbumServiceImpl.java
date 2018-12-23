package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumMessage;
import com.baizhi.mapper.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

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
}

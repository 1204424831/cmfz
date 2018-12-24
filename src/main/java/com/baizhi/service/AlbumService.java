package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumMessage;

import java.util.List;

public interface AlbumService {
    public AlbumMessage querySome(Integer rows, Integer page);

    public Album queryOne(Integer id);

    public void insertOne(Album a);

    public List<Album> queryAll();
}

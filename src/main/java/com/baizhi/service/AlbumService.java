package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumMessage;

public interface AlbumService {
    public AlbumMessage querySome(Integer rows, Integer page);

    public Album queryOne(Integer id);

    public void insertOne(Album a);
}

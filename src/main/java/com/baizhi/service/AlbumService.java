package com.baizhi.service;

import com.baizhi.entity.*;

import java.util.List;

public interface AlbumService {
    public AlbumMessage querySome(Integer rows, Integer page);

    public Album queryOne(Integer id);

    public void insertOne(Album a);

    public List<Album> queryAll();

    public List<StuMess> queryAllStu();

    public List<StuMess> querySomeStu();

    public Album1 queryOneAlu(Integer id);

    public List<Chapter1> queryAllCha(Integer aid);
}

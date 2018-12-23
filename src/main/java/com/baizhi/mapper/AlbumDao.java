package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumDao extends Mapper<Album> {
    public List<Album> querySomAlbum(@Param("rows") Integer rows, @Param("page") Integer page);

}

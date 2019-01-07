package com.baizhi.mapper;

import com.baizhi.entity.Album;
import com.baizhi.entity.Album1;
import com.baizhi.entity.StuMess;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumDao extends Mapper<Album> {
    public List<Album> querySomAlbum(@Param("rows") Integer rows, @Param("page") Integer page);

    public List<Album> queryAllAlbum();

    public List<StuMess> queryAllAlb();

    public List<StuMess> querySomeAlb();

    public Album1 queryOneAlb(Integer id);
}

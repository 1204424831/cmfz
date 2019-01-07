package com.baizhi.mapper;

import com.baizhi.entity.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    public Integer queryFirstWeek();

    public Integer querySecondWeek();

    public Integer queryThreedWeek();

    public List<Province> queryProvince();

    public List<Province> queryProMan();

    public List<Province> queryProWom();

    public User1 queryOneUser(String phone);

    public User2 queryOneUser1(String phone);

    public List<User3> queryFiveUser();
}

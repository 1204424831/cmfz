package com.baizhi.service;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.UserMessage;

import java.util.List;
import java.util.Map;

public interface UserService {
    public UserData querySome();

    public Map<String, List<Province>> queryProvince();

    public Map<String, List<Province>> queryProMan();

    public Map<String, List<Province>> queryProWom();

    public UserMessage querySomeUser(Integer page, Integer rows);

    public void updateOne(User u);

    public List<User> queryAll();
}

package com.baizhi.service;

import com.baizhi.entity.*;

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

    public User1 queryOneUser(String phone);

    public User2 queryOneUser1(String phone);

    public void insertOne(User u);

    public User queryOneUse(Integer id);

    public List<User3> queryFiveUser();
}

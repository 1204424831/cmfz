package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.*;
import com.baizhi.mapper.UserDao;
import com.github.pagehelper.PageHelper;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserData querySome() {
        Integer first = userDao.queryFirstWeek();
        Integer second1 = userDao.querySecondWeek();
        Integer second = second1 - first;
        Integer threed1 = userDao.queryThreedWeek();
        Integer threed = threed1 - second1;
        Integer[] data = {threed, second, first};
        UserData u = new UserData(data);
        return u;
    }

    @Override
    public Map<String, List<Province>> queryProvince() {
        List<Province> list = userDao.queryProvince();
        Map<String, List<Province>> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @Override
    public Map<String, List<Province>> queryProMan() {
        List<Province> list = userDao.queryProMan();
        Map<String, List<Province>> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @Override
    public Map<String, List<Province>> queryProWom() {
        List<Province> list = userDao.queryProWom();
        for (Province p : list) {
            System.out.println(p);
        }
        Map<String, List<Province>> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @Override
    public UserMessage querySomeUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> list = userDao.selectAll();
        User u = new User();
        Integer total = userDao.selectCount(u);
        UserMessage um = new UserMessage(total, list);
        return um;
    }

    public void updateOne(User u) {

        userDao.updateByPrimaryKey(u);
        Integer first = userDao.queryFirstWeek();
        Integer second1 = userDao.querySecondWeek();
        Integer second = second1 - first;
        Integer threed1 = userDao.queryThreedWeek();
        Integer threed = threed1 - second1;
        Integer[] data = {threed, second, first};
        UserData u1 = new UserData(data);
        System.out.println(JSONObject.toJSONString(data));

        List<Province> list = userDao.queryProMan();
        List<Province> list1 = userDao.queryProWom();
        Map<String, List<Province>> map = new HashMap<>();
        map.put("man", list);
        map.put("wom", list1);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-ee3c1f1bd50d4fc58d6b27a4770bd432");
        goEasy.publish("cmfz", JSONObject.toJSONString(data), new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.print("消息发布成功。");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.print("消息发布失败, 错误编码：" + error.getCode() + " 错误信息： " + error.getContent());
            }
        });
        goEasy.publish("person", JSONObject.toJSONString(map), new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.print("消息发布成功。");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.print("消息发布失败, 错误编码：" + error.getCode() + " 错误信息： " + error.getContent());
            }
        });
    }

    @Override
    public List<User> queryAll() {
        List<User> list = userDao.selectAll();
        return list;
    }

    public User queryOneUse(Integer id) {
        User u = userDao.selectByPrimaryKey(id);
        return u;
    }

    @Override
    public List<User3> queryFiveUser() {
        List<User3> list = userDao.queryFiveUser();
        return list;
    }

    @Override
    public User1 queryOneUser(String phone) {
        User1 u = userDao.queryOneUser(phone);
        return u;
    }

    @Override
    public User2 queryOneUser1(String phone) {
        User2 u = userDao.queryOneUser1(phone);
        return u;
    }

    @Override
    public void insertOne(User u) {
        userDao.insert(u);
    }
}

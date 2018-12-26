package com.baizhi.service;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.UserMessage;
import com.baizhi.mapper.UserDao;
import com.github.pagehelper.PageHelper;
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
    }

    @Override
    public List<User> queryAll() {
        List<User> list = userDao.selectAll();
        return list;
    }
}

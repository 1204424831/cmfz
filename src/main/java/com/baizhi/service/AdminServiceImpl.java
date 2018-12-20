package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin queryOne(String name, String password, String code, String ccode) {
        if (!code.equalsIgnoreCase(ccode)) throw new RuntimeException("验证码不正确！");
        if (name == null) throw new RuntimeException("姓名不能为空！");
        Admin a = null;
        Admin ad = new Admin();
        ad.setName(name);
        a = adminDao.selectOne(ad);
        if (a == null) throw new RuntimeException("该成员不是管理员");
        if (password == null) throw new RuntimeException("密码不能为空！");
        if (!a.getPassword().equals(password)) throw new RuntimeException("密码不正确！");
        return a;
    }
}

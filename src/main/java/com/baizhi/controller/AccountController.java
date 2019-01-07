package com.baizhi.controller;

import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.entity.User1;
import com.baizhi.entity.User2;
import com.baizhi.service.UserService;
import com.baizhi.util.Md5Util;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    private Md5Util md5Util;

    @RequestMapping("/login")
    public Object login(String phone, String password, String code, HttpSession session) {
        if (phone == null || (password == null || code == null)) {
            return new Error("-200", "参数不能为空");
        } else {
            if (code == null) {
                User1 u = null;
                u = userService.queryOneUser(phone);
                if (u == null) {
                    return new Error("-200", "该用户还未注册");
                }
                if (!u.getPassword().equals(password)) {
                    return new Error("-200", "密码输入不正确");
                }
                return u;
            } else {
                String ccode = (String) session.getAttribute("code");
                if (!code.equals(ccode)) {
                    return new Error("-200", "验证码输入错误");
                }
                return userService.queryOneUser(phone);
            }
        }
    }

    @RequestMapping("regist")
    public Object regist(String phone, String password) {
        if (phone == null || password == null) {
            return new Error("-200", "参数不能为空");
        } else {
            User1 user = null;
            user = userService.queryOneUser(phone);
            if (user != null) {
                return new Error("-200", "该手机号已经被注册");
            }
            User u = new User();
            u.setPhone(phone);
            u.setPassword(password);
            u.setStatus("n");
            userService.insertOne(u);
            User2 u1 = userService.queryOneUser1(phone);
            return u1;
        }
    }

    @RequestMapping("modify")
    public Object modify(Integer uid, String gender, MultipartFile photo, HttpSession session, String location, String description, String nickname, String province, String city, String password) {
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath(photo.getOriginalFilename());
        File f = new File(realPath);
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(new FileInputStream(f), f.length(), FilenameUtils.getExtension(f.getName()), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (uid == null) {
            return new Error("-200", "参数不能为空");
        } else {
            User u = userService.queryOneUse(uid);
            u.setSex(gender);
            u.setPhone(storePath.getGroup() + "/" + storePath.getPath());
            u.setSign(description);
            u.setName(nickname);
            u.setProvince(province);
            u.setCity(city);
            char[] str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456".toCharArray();

            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i <= 4; i++) {
                //随机生成0到str长度之间的数字做为下标
                int randomIndex = random.nextInt(str.length);
                //追加到sb对象
                sb.append(str[randomIndex]);
            }
            String salt = sb.toString();
            password = md5Util.encryption("123456" + salt);
            u.setSalt(salt);
            u.setPassword(password);
            userService.updateOne(u);
            return u;
        }
    }
}

package com.baizhi.controller;

import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserDao;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cmfz")
public class CmfzController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/first_page")
    public Object test(String uid, String type, String sub_type) {
        if (uid == null || type == null) {
            return new Error(null, "参数不能为空");
        } else {
            if (type.equals("all")) {
                User u = userDao.selectByPrimaryKey(uid);
                Map<String, Object> map = new HashMap<>();
                map.put("header", bannerService.queryAll());
                map.put("album", albumService.querySomeStu());
                map.put("article", articleService.querySomeStu(u.getId()));
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                map.put("album", albumService.queryAllStu());
                return map;
            } else {
                if (sub_type != null) {
                    User u = userDao.selectByPrimaryKey(uid);
                    if (sub_type.equals("ssyj")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", articleService.queryAllStu(u.getId()));
                        return map;
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", articleService.queryOtherStu(u.getId()));
                        return map;
                    }

                } else {
                    return new Error(null, "思的类型不能为空");
                }
            }
        }
    }
}

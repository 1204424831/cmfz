package com.baizhi.controller;

import com.baizhi.entity.Gurn;
import com.baizhi.mapper.GurnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gurn")
public class GurnController {
    @Autowired
    GurnDao gurnDao;

    @RequestMapping("queryAll")
    public List<Gurn> queryAll() {
        List<Gurn> list = gurnDao.selectAll();
        return list;
    }
}

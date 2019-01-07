package com.baizhi.controller;

import com.baizhi.entity.User3;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private UserService userService;

    @RequestMapping("queryFive")
    public List<User3> queryFiveUser(Integer uid) {
        List<User3> list = userService.queryFiveUser();
        return list;
    }
}

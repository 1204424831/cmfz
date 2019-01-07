package com.baizhi.controller;

import com.baizhi.entity.Error;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/wen")
    public Object wen(Integer id, Integer uid) {
        if (id == null || uid == null) {
            return new Error(null, "参数不能为空");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("introduction", albumService.queryOneAlu(id));
            map.put("list", albumService.queryAllCha(id));
            return map;
        }
    }
}

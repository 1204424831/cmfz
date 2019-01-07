package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.UserMessage;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/querySomeUser")
    public UserData querySomeUser() {
        UserData data = userService.querySome();
        return data;
    }

    @RequestMapping("/queryAllPro")
    public Map<String, List<Province>> queryAllPro(String sex) {
        Map<String, List<Province>> map1 = null;
        if (sex.equals("女")) {
            map1 = userService.queryProWom();
        }
        if (sex.equals("男")) {
            map1 = userService.queryProMan();
        }
        System.out.println(JSONObject.toJSONString(map1) + "--------");
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-ee3c1f1bd50d4fc58d6b27a4770bd432");
        goEasy.publish("140", JSONObject.toJSONString(map1));
        return map1;
    }

    @RequestMapping("/queryProvinces")
    public Map<String, List<Province>> queryProvinces(String sex) {
        Map<String, List<Province>> map = null;
        if (sex.equals("男")) {
            map = userService.queryProMan();
        }
        if (sex.equals("女")) {
            map = userService.queryProWom();
        }
        return map;
    }

    @RequestMapping("querySome")
    public UserMessage querySomeUser(Integer page, Integer rows) {
        UserMessage um = userService.querySomeUser(page, rows);
        return um;
    }

    @RequestMapping("updateOne")
    public void updateOne(User u) {

        userService.updateOne(u);


    }

    @RequestMapping("out")
    public void outExport() {
        List<User> list = userService.queryAll();
        for (User a : list) {
            String url = "F:\\cmfz\\cmfz_test\\src\\main\\webapp" + a.getHeadPic();
            a.setHeadPic(url);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息", "用户"),
                User.class, list);
        try {
            workbook.write(new FileOutputStream(new File("D:/allUsers.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("init")
    public void initExport() {
//        try {
//            ImportParams params = new ImportParams();
//            params.setReadSingleCell(true);
//            params.setTitleRows(1);
//            ExcelImportResult<Map> result = ExcelImportUtil.importExcelMore(
//                    new File("D:/allUsers.xls"),
//                    Map.class, params);
//            for (int i = 0; i < result.getList().size(); i++) {
//                Map map = result.getList().get(i);
//                System.out.println(map);
//            }
//        }catch (Exception e){
//
//        }
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        //params.setSheetNum(9);
        params.setNeedSave(true);
        long start = new Date().getTime();
        List<User> result = ExcelImportUtil.importExcel(new File(
                "D:/allUsers.xls"), User.class, params);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
        }
    }
}

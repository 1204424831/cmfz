package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("queryOne")
    public String queryOne(String name, String password, String code, HttpSession session) {
        try {
            String ccode = (String) session.getAttribute("code");
            ccode = ccode.toLowerCase();
            code = code.toLowerCase();
            System.out.println(code + "------" + ccode);
            Admin a = adminService.queryOne(name, password, code, ccode);
            session.setAttribute("name", name);
            return "ok";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @RequestMapping("/code")
    public void code(HttpSession session, ServletResponse response) throws IOException {
        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();
        session.setAttribute("code", code);
        cvc.write(response.getOutputStream());
    }
}

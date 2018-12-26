package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "用户手机号", needMerge = true)
    private String phone;
    @Excel(name = "用户密码", needMerge = true)
    private String password;
    @Excel(name = "密码加盐", needMerge = true)
    private String salt;
    @Excel(name = "用户签名", needMerge = true)
    private String sign;
    @Excel(name = "用户头像", needMerge = true, type = 2, width = 30, height = 40)
    private String headPic;
    @Excel(name = "用户姓名", needMerge = true)
    private String name;
    @Excel(name = "用户法号", needMerge = true)
    private String dharma;
    @Excel(name = "用户性别", needMerge = true)
    private String sex;
    @Excel(name = "用书省份", needMerge = true)
    private String province;
    @Excel(name = "用户城市", needMerge = true)
    private String city;
    @Excel(name = "账号状态", needMerge = true)
    private String status;
    @Excel(name = "上传日期", format = "YYYY年MM月dd日", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date regDate;
}

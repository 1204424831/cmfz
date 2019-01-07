package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User3 implements Serializable {
    private String farmington;
    private String nickname;
    private String gender;
    private String photo;
    private String location;
    private String province;
    private String city;
    private String description;
    private String phone;
}

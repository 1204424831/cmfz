package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner1 implements Serializable {
    private String thumbnail;
    private String desc1;
    private Integer Id;
}

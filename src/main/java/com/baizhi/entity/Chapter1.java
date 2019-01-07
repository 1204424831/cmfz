package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter1 implements Serializable {
    private String title;
    private String download_url;
    private String size;
    private String duration;
}

package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑主题", needMerge = true)
    private String title;
    @Excel(name = "章节数量", needMerge = true)
    private Integer count;
    @Excel(name = "封面路径", needMerge = true, type = 2, width = 30, height = 40)
    private String coverImg;
    @Excel(name = "专辑好评", needMerge = true)
    private String score;
    @Excel(name = "专辑作者", needMerge = true)
    private String author;
    @Excel(name = "播音员", needMerge = true)
    private String broadcast;
    @Excel(name = "专辑简介", needMerge = true)
    private String brief;
    @Excel(name = "上传日期", format = "YYYY年MM月dd日", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date putDate;
    @ExcelCollection(name = "章节")
    @Transient
    private List<Chapter> children;

}

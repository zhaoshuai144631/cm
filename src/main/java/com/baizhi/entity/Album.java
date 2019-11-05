package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {
    @Id
    private String id;
    private String cover;//封面
    private String title;//标题
    private Integer count;//章节数
    private Double score;//评分
    private String  broadcast;//播音员
    private String brief;//简介
    private Teacher teacher;

    //反序列化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //序列化
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    private String teacherId;
}

package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Pic implements Serializable {
    @Id//mapper专属注解
    private String id;
    private String name;
    private String cover;
    private String description;
    @JSONField(format = "yyyy-MM-dd")//序列化
    //反序列化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String status;

}

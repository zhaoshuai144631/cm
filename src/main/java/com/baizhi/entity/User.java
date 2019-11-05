package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id//通用mapper标识ID的注解
    @Excel(name="编号")
    private String id;
    @Excel(name="用户名")
    private String username;
    @Excel( name="真名")
    private String realname;
    private String salt;
    private String password;
    private String phone;
    private String province;
    private String city;
    @Excel(name="photo",type = 2)
    private String photo;
    private String sex;
    @JSONField(format = "yyyy-MM-dd")//序列化
    //反序列化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="时间", format = "yyyy-MM")
    private Date date;
    private String teacherId;
    @ExcelEntity
    private Teacher  teacher;
    @ExcelCollection(name = "文章")
    private List<Article> list;
}

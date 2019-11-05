package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.xml.bind.ValidationEvent;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "文章")
public class Article implements Serializable {
    @Id
    private String id;
    @Excel(name="标题")
    private  String title;//标题
    private String content;//内容
    //反序列化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //序列化
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    @Excel(name="作者")
    private String author;

}

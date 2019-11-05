package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "上师")
public class Teacher implements Serializable {
    @Id//通用mapper标志主键
    private String id;
    @Excel(name="姓名")
    private String name;
    private String photo;
    private String status;
    @Excel(name="性别")
    private String sex;
}

package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class Chapter implements Serializable {
    //章节表
    @Id
    private String id;//id
    private String title;//章节名字
    private String sizes;//文件大小
    private String duration;//时长
    private String name;//文件名
    //反序列化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //序列化
    @JSONField(format = "yyyy-MM-dd")
    private Date date;//创建日期
    private String albumId;//专辑id;
}

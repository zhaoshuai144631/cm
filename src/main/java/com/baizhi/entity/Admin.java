package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    @Id//通用mapper专属注解
    private String id;
    private String username;
    private String password;
    private String realname;

}

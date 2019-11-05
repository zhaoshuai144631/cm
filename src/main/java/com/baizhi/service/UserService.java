package com.baizhi.service;

import com.baizhi.entity.Month;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> selectByTeacherId(Integer page,Integer rows,String id);
    List<User> export();
    List<Month> queryMen();
    List<Month> queryWomen();
}

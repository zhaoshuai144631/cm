package com.baizhi.dao;

import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDAO extends Mapper<User> {
    List<Month> queryMen();
    List<Month> queryWomen();
}

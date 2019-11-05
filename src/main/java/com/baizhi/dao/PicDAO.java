package com.baizhi.dao;

import com.baizhi.entity.Pic;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface  PicDAO extends Mapper<Pic> {
    List<Pic> showAll();
}

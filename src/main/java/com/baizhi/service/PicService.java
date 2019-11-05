package com.baizhi.service;


import com.baizhi.entity.Pic;

import java.util.Map;

public interface PicService {
    Map<String,Object> selectAll(Integer page,Integer rows);
    String add(Pic pic);
    void edit(Pic pic);
    void delete(Pic pic);
}

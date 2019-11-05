package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    public Map<String,Object> selectAll(Integer page,Integer rows,String albumId);
    public String add(Chapter chapter);
    public void edit(Chapter chapter);
    public void del(Chapter chapter);
}

package com.baizhi.service;

import com.baizhi.entity.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ArticleService {
    public Map<String ,Object> selectAll(Integer page, Integer rows);
    void add(Article article);
    void edit(Article article);
    void del(Article article);
}

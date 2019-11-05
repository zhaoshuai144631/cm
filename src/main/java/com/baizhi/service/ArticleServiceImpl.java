package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public void edit(Article article) {
        int i = articleDAO.updateByPrimaryKeySelective(article);
        if (i == 0) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(Article article) {
            int i = articleDAO.delete(article);


        }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
            Map<String,Object> map=new HashMap<>();
            Article article=new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> list = articleDAO.selectByRowBounds(article, rowBounds);
        //获得总条数
        int i = articleDAO.selectCount(article);
        map.put("page",page);//当前页
        map.put("rows",list);//所有数据
        map.put("total",i%rows==0?i/rows:i/rows+1);//总页数
        map.put("records",i);//总条数
        return map;
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setDate(new Date());
        int i = articleDAO.insertSelective(article);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
    }
}

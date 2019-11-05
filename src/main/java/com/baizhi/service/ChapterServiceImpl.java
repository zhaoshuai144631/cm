package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.loops.ProcessPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    //注入
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public String add(Chapter chapter) {
        //添加UUID
        chapter.setId(UUID.randomUUID().toString());
        int i = chapterDAO.insertSelective(chapter);
        if(i==0){
            throw new  RuntimeException("添加失败");
        }
        return chapter.getId();
    }

    @Override
    public void edit(Chapter chapter) {
        chapterDAO.updateByPrimaryKeySelective(chapter);

    }

    @Override
    public void del(Chapter chapter) {
        int i = chapterDAO.delete(chapter);
        if(i==0){
            throw new RuntimeException("删除失败");
        }

    }

    //查询分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows, String albumId) {
        //创建MAP
        Map<String,Object> map=new HashMap<>();
        //创建章节对象
        Chapter chapter = new Chapter();
        //传入数据作为查询条件
        chapter.setAlbumId(albumId);
        //创建RowBounds对象
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        //查询
        List<Chapter> list = chapterDAO.selectByRowBounds(chapter, rowBounds);
        //查询所有数据条数
        int i = chapterDAO.selectCount(chapter);
        //存入map
        map.put("page",page);
        map.put("rows",list);
        map.put("total",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);

        return map;
    }
}

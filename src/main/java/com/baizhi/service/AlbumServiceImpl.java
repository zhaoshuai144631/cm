package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.TeacherDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Teacher;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl  implements  AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private TeacherDAO teacherDAO;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String ,Object> map=new HashMap<>();
        Album album=new Album();
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        List<Album> list = albumDAO.selectByRowBounds(album, rowBounds);
        for (Album a : list) {
            Teacher teacher = teacherDAO.selectByPrimaryKey(a.getTeacherId());
            a.setTeacher(teacher);


        }
        //查询数据总数量
        int i = albumDAO.selectCount(album);
        map.put("page",page);
        map.put("rows",list);
        map.put("total",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);
        return map;

    }

    @Override
    public Album selectOne(String id) {
        Album album=new Album();
        album.setId(id);
        Album album1 = albumDAO.selectOne(album);
        return album1;
    }

    @Override
    public String add(Album album) {
        //创建上师对象
 //       Teacher teacher =new Teacher();
        //传入查询条件 专辑的作者就是上师的name;
   //     teacher.setName(album.getAuthor());
        //查询出具体的上师
   //     Teacher teacher1 = teacherDAO.selectOneByExample(teacher);
        //上师的ID就是专辑的上师ID，添加专辑的上师ID
     //   album.setTeacherId(teacher1.getId());
        //添加UUID
        album.setId(UUID.randomUUID().toString());
        //添加章节数
        album.setCount(0);
        //添加创建时间
        //album.setDate(new Date());
//        album.setTeacherId("1");
        int i = albumDAO.insertSelective(album);
        System.out.println(album);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return album.getId();
    }

    @Override
    public void del(Album album) {
        int i = albumDAO.delete(album);
        if(i==0){
            throw new RuntimeException("删除失败");
        }

    }

    @Override
    public void update(Album album) {
        //先判断图片是否修改
        if("".equals(album.getCover())){
            album.setCover(null);
        }
        try {
            albumDAO.updateByPrimaryKeySelective(album);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }

    }
}

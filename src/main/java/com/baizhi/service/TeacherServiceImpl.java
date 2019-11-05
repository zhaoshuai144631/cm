package com.baizhi.service;

import com.baizhi.dao.TeacherDAO;
import com.baizhi.entity.Teacher;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
   @Autowired
    private TeacherDAO teacherDAO;
   //查询所有方法
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        //创建map
        Map<String, Object> map = new HashMap<String, Object>();
        //创建teacher对象
        Teacher teacher = new Teacher();
        //创建rowBounds对象
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //取得当前页所有数据list
        List<Teacher> list = teacherDAO.selectByRowBounds(teacher, rowBounds);
        //获得teacher表中所有的数据数量
        int i = teacherDAO.selectCount(teacher);
        //把以上数据存入map
        map.put("page",page); //当前页
        map.put("rows",list);//当前页所有对象信息
        map.put("total",i%rows==0?i/rows:i/rows+1);//一共多少页
        map.put("records",i);//所有数据条数



        return map;
    }
    //添加方法
    @Override
    public String add(Teacher teacher) {
        //添加UUID
        teacher.setId(UUID.randomUUID().toString());
        int i = teacherDAO.insertSelective(teacher);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return teacher.getId();
    }
//删除

    @Override
    public void delete(Teacher teacher) {
        int i = teacherDAO.delete(teacher);
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<Teacher> queryAll() {
        List<Teacher> list = teacherDAO.selectAll();
        return list;
    }

    //修改
    @Override
    public void update(Teacher teacher) {
        //先判断图片是否用修改
        if("".equals(teacher.getPhoto())){
            teacher.setPhoto(null);
        }
        try{
            //如果有图片直接修改 如果没有传入图片就不用修改
            int i = teacherDAO.updateByPrimaryKeySelective(teacher);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改失败");

        }

    }
}

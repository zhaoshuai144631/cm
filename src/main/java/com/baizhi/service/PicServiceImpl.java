package com.baizhi.service;

import com.baizhi.dao.PicDAO;
import com.baizhi.entity.Pic;
import com.sun.corba.se.impl.interceptors.PICurrent;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service//工厂可以实例此类
@Transactional//事务控制
public class PicServiceImpl implements PicService {
    @Autowired //注入DAO
    private PicDAO picDAO;
    @Override
    public String add(Pic pic) {
        //加入UUID
        pic.setId(UUID.randomUUID().toString());
        //加入Date日期
        pic.setDate(new Date());
        //添加后返回一个Int类型数值 如果是0添加失败如果是1添加成功
        int i = picDAO.insertSelective(pic);
        if (i==0){
            throw new RuntimeException("添加失败");

        }
        return pic.getId();

    }


    @Override
    public void delete(Pic pic) {
        int  i = picDAO.delete(pic);
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public void edit(Pic pic) {
        //有些字段不用修改
        //如果没有修改救把cover设置为nULL
        if("".equals(pic.getCover())){
            pic.setCover(null);
        }
        try {
            int i = picDAO.updateByPrimaryKeySelective(pic);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        //创建图片对象
        Pic pic=new Pic();
        //创建rowBounds对象
        System.out.println(page+"++++"+rows);
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);//空指针
        System.out.println(rowBounds);
        //得到分页的List
        List<Pic> list = picDAO.selectByRowBounds(pic, rowBounds);
        //获得数据总条数
        int i = picDAO.selectCount(pic);

        Map<String,Object>map=new HashMap<>();
        map.put("page",page);//传入当前页
        map.put("rows",list);//传入当前页的数据
        map.put("total",i%rows==0?i/rows:i/rows+1);//获得总页数
        map.put("records",i);//总共多少条数据
        //将map返回
       return map;
    }
}

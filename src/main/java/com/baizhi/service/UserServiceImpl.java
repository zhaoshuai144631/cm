package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> export() {
        return userDAO.selectAll();
    }



    @Override
    public List<Month> queryWomen() {
        List<Month> list = userDAO.queryWomen();
        return list;
    }

    @Override
    public List<Month> queryMen() {
        List<Month> list = userDAO.queryMen();
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectByTeacherId(Integer page, Integer rows, String id) {
        //创建map对象
        Map<String,Object> map=new HashMap<>();
        //创建User对象
        User user =new User();
        //设置查询条件 根据通用mapper中将数据传入对象即为查询条件
        user.setTeacherId(id);
        //创建rowBounds对象
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        //根据rowBounds查询得到LIST
        List<User> list = userDAO.selectByRowBounds(user, rowBounds);
        //获取数据总条数
        int i = userDAO.selectCount(user);
        //将以上四条数据传入map
        map.put("page",page);
        map.put("rows",list);
        map.put("total",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);//数据总条数

        return map;
    }
}

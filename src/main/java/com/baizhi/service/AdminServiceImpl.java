package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void login(Admin admin, String inputCode, HttpServletRequest request) {
        //创建session对象
        //从session对象中获取最初的code
        HttpSession session = request.getSession();
        String  code = (String) session.getAttribute("code");
        //最初的code和前台输入的code做出比对
        //调用equals的对象需要时最初的code因为input的code可能时null 这样做时为了避免空指针异常
        if(code.equals(inputCode)){
            //将admin传入通用mapper的selectOne方法中获得完整Admin对象
            Admin loginAdmin=adminDAO.selectOne(admin);
            if(loginAdmin!= null){
                //判断得到的admin对象是否为空 为空说明通过以上参数没有从数据库中获取到对象
                //不为空说明以上数据正确获取到一个正确的对象
                session.setAttribute("loginAdmin",loginAdmin);
            }else{
                //错误的内容在此截断 并且提供错误信息
                throw new RuntimeException("用户名或者密码错误");
            }

        }else{
            //验证码错误在此截断程序 并且提供错误信息
            throw new RuntimeException("验证码错误");

        }
    }
}

package com.baizhi.controller;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public Map<String ,Object> login(Admin admin , String inputCode , HttpServletRequest request){
        //创建Map对象
        Map<String,Object>map=new HashMap<>();
        try{
            adminService.login(admin,inputCode,request);
            //如果不报错则进行下一步 在map里存储状态为true
            map.put("status",true);
        }catch(Exception e){
            //如果调用service方法报错则存储状态为false
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        //将赋值后的map返回前台
        return map;
    }

}

package com.baizhi.controller;

import com.baizhi.entity.Teacher;
import com.baizhi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//ajax控制层 返回json格式 JQGrid封装了Ajax;
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    //注入
    private TeacherService teacherService;
    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page,Integer rows){
        //创建map对象
        Map<String,Object> map=teacherService.selectAll(page,rows);
            return map;
    }
    //增删改操作
    @RequestMapping("edit")
    public Map<String ,Object> edit(String oper, Teacher teacher, HttpServletRequest request){
        //创建map对象
        Map<String,Object> map=new HashMap<>();
        //判断oper是那种方法
        try{
            if("add".equals(oper)) {
                //执行添加方法
                String id = teacherService.add(teacher);
                //将id存入map
                map.put("message",id);
            }
            if("edit".equals(oper)){
                teacherService.update(teacher);
            }
            if("del".equals(oper)){
                teacherService.delete(teacher);
            }
            map.put("status",true);

        }catch(Exception e){
            e.printStackTrace();
            map.put("status",false);
            //存入错误信息
            map.put("message",e.getMessage());
        }
        return map;

    }

    //文件上传
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile photo, String id,HttpServletRequest request){
        //创建map对象
        Map<String,Object> map=new HashMap<>();
        try{
            //获取文件真实路径
            String realPath = request.getServletContext().getRealPath("/back/img");//存入IMG文件夹
            //文件上传
           photo.transferTo(new File(realPath,photo.getOriginalFilename()));
           //上传成功 修改对象信息并且 存入map状态
            Teacher teacher=new Teacher();
            teacher.setId(id);
            teacher.setPhoto(photo.getOriginalFilename());

            //修改对象信息
            teacherService.update(teacher);
            map.put("status",true);
        }catch(Exception e){
    e.printStackTrace();
    map.put("status",false);
    throw new RuntimeException("文件上传失败");
        }
   return map;
    }
    //下拉框
    @RequestMapping("teacherName")
    @ResponseBody
    public void queryTeacherName(HttpServletResponse response)throws IOException{
        //获取所有上师对象集合
        List<Teacher> teachers = teacherService.queryAll();
        //创建stringBuilder对象
        StringBuilder sb=new StringBuilder();
        sb.append("<select>");//拼接一个下拉框选项
        //遍历上师集合
        teachers.forEach(teacher -> {
            //遍历一次创建一个Option对象,用builder对象拼接一下
            sb.append("<option value=").append(teacher.getId()).append(">").append(teacher.getName()).append("</option>");
        });
        sb.append("</select>");//拼接一个尾标签
        //设置响应格式
        response.setContentType("text/html;charset=UTF-8");
        //以json格式打回前台
        response.getWriter().print(sb.toString());

    }

}

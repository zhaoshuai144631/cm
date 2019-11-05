package com.baizhi.controller;

import com.baizhi.entity.Pic;
import com.baizhi.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("pic")
@RestController//ajax异步rest=responseJson+controller,加了这个注解返回值以json格式返回前台
public class PicController {
    @Autowired
    private PicService picService;
 @RequestMapping("selectAll")
    public Map<String ,Object> selectAll(Integer page,Integer rows){
    //调用service 获取map 以json格式返回前台
     Map<String, Object> map = picService.selectAll(page, rows);
     //返回map
    return map;
    }


    //


    @RequestMapping("edit")
    public Map<String,Object> add(String oper, Pic pic,HttpServletRequest request){
     Map<String,Object> map=new HashMap<String,Object>();
     try {
         if ("add".equals(oper)) {
             //如果oper是添加方法 执行以下代码
             //如果返回ID没有异常那么将ID存入map 并为之赋值状态成功
             String id = picService.add(pic);
             map.put("message",id);

         }
         if ("edit".equals(oper)) {
             picService.edit(pic);
         }
         if ("del".equals(oper)) {
                picService.delete(pic);
         }
         map.put("status",true);
     }catch(Exception e){
         e.printStackTrace();
         //失败跳入catch并设置状态为false
         map.put("status",false);
         //将错误信息存入
         map.put("message",e.getMessage());

     }

     return map;

    }
    //上传操作
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile cover, String id, HttpServletRequest request){
     //获真实路径
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            //获取真实路径
            String realPath = request.getServletContext().getRealPath("/back/img");//存入img
            //上传文件
            cover.transferTo(new File(realPath,cover.getOriginalFilename()));
            //存入map 没有异常存入true
                Pic pic=new Pic();
                //将id传入pic对象
                pic.setId(id);
                //将文件名传入pic对象
                pic.setCover(cover.getOriginalFilename());
                //修改pic数据
                picService.edit(pic);
            map.put("status",true);
        } catch (IOException e) {
            e.printStackTrace();
            //有异常存入false
            map.put("status",false);
        }
        //返回json格式的map
        return map;
    }
}

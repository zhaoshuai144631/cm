package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page, Integer rows, HttpServletRequest request){
        Map<String, Object> map = albumService.selectAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper ,Album album,HttpServletRequest request){
        //创建map
        Map<String ,Object> map=new HashMap<>();
        //判断是增删改哪种方法
      try {
          if ("add".equals(oper)) {
              String id = albumService.add(album);
              map.put("message", id);
          }

          if ("edit".equals(oper)) {
              albumService.update(album);
          }
          if ("del".equals(oper)) {
              albumService.del(album);
          }
          map.put("status",true);
      }catch(Exception e){
        e.printStackTrace();
        map.put("status",false);
        map.put("message",e.getMessage());
      }
    return map;
    }
    @RequestMapping("upload")
    public Map<String ,Object> upload(MultipartFile cover,String id,HttpServletRequest request){
        //创建map对象
        Map<String,Object> map=new HashMap<>();
        try {
            //获取真实路径
            String realPath = request.getServletContext().getRealPath("/back/img");
            //文件上传
            cover.transferTo(new File(realPath,cover.getOriginalFilename()));
            //上传成功将信息存入对象并且修改对象信息
            Album album=new Album();
            album.setId(id);
            album.setCover(cover.getOriginalFilename());
            albumService.update(album);
            //存入状态
            map.put("status",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",false);
            throw new RuntimeException("上传文件失败");
        }
        return map;

    }
}

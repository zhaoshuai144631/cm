package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("chapter")
@RestController
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping("selectAll")
    public Map<String, Object> selectAll(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = chapterService.selectAll(page, rows, albumId);
        return map;

    }
    //增删改操作
    @RequestMapping("edit")
    public Map<String ,Object> edit(String oper, Chapter chapter, HttpServletRequest request){
        //创建map对象
        Map<String,Object> map=new HashMap<>();
        //判断oper是那种方法
        try{
            if("add".equals(oper)) {
                //执行添加方法
                String id = chapterService.add(chapter);
                //将id存入map
                map.put("message",id);
            }
            if("edit".equals(oper)){
                chapterService.edit(chapter);
            }
            if("del".equals(oper)){
                chapterService.del(chapter);
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
    public Map<String, Object> upload(MultipartFile name, String id,String albumId ,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(name);
        //处理文件上传
        try {
            String realPath = request.getServletContext().getRealPath("/back/music");

            File file = new File(realPath, name.getOriginalFilename());//空指针异常
            name.transferTo(file);
            //修改文件名称
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setAlbumId(albumId);
            chapter.setName(name.getOriginalFilename());
            long size = name.getSize();//上传文件的大小；
            BigDecimal size1 = new BigDecimal(size);//精确
            BigDecimal mod = new BigDecimal(1024);//B/1024/1024 把1024精确
            BigDecimal realSize = size1.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入
            chapter.setSizes(realSize + "MB");
            //获取文件时长  导入da di yang jar
            Encoder encoder = new Encoder();
            long duration = encoder.getInfo(file).getDuration();
            //duration/1000/60 计算出多少分钟
            //duration/1000%60 计算出多少秒
            chapter.setDuration(duration / 1000 / 60 + ":" + duration / 1000 % 60);
            chapterService.edit(chapter);
            //修改专辑数量
            Album album= albumService.selectOne(albumId);
            album.setCount(album.getCount()+1);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;

    }

}

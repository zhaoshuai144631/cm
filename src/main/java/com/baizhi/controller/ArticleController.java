package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("article")
@RestController
public class ArticleController {
    //注入
    @Autowired
    private ArticleService
            articleService;

    //分页查询
    @RequestMapping("selectAll")
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.selectAll(page, rows);
        return map;

    }

    @RequestMapping("upload")//图片上传
    public Map<String, Object> upload(MultipartFile wen, HttpServletRequest request) {

        //文件上传 wen是上传图片 图片的名称 也就是参数固定名字
        //创建一个Map
        Map<String, Object> map = new HashMap<>();
        try {
            wen.transferTo(new File(request.getServletContext().getRealPath("article/img"), wen.getOriginalFilename()));
            map.put("error", 0);//成功返回0 就是没有错误的意思
            map.put("url", "http://localhost:8989/cm/article/img/" + wen.getOriginalFilename());
        } catch (Exception e) {
            map.put("error", 1);

        }
        return map;
    }

    @RequestMapping("space")//图片空间,获取某个路径下所有的图片
    public Map<String, Object> space(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("article/img"));//创建目录对象
        File[] files = file.listFiles();//拿到所有的文件
        List<Object> list = new ArrayList<>();
        for (File img : files) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("is_dir", false);//是否是文件夹
            map1.put("has_file", false);//是否又文件
            map1.put("filesize", img.length());//文件大小
            map1.put("is_photo", true);//是否是图片
            map1.put("filetype", FilenameUtils.getExtension(img.getName()));//图片的类型
            map1.put("filename", img.getName());//获得文件图片名
            map1.put("datetime", "2011-06-06 00:11:11");//创建时间
            //遍历一次一个图片文件所有信息存入全部MAp
            //遍历一次图片获得一个MAP,把每次得到的MAP存入list
            list.add(map1);

        }
        map.put("file_list", list);
        map.put("total_count", list.size());
        map.put("current_url", "http://localhost:8989/cm/article/img/");//图片位置

        return map;
    }

    @RequestMapping("add")
    public void add(Article article) {
        articleService.add(article);
    }

    @RequestMapping("edit")
    public void edit(Article article) {
        articleService.edit(article);
    }

    @RequestMapping("del")
    public void del(String id) {
        Article article = new Article();
        article.setId(id);
        articleService.del(article);
    }

}



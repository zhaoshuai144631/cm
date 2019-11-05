package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page,Integer rows,String id){
        Map<String, Object> map = userService.selectByTeacherId(page, rows, id);
        return map;

    }
    @RequestMapping("export")
    public void export(HttpServletResponse resp){
//        准备数据
//        userService...
        List<User> list = userService.export();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"), com.baizhi.entity.User.class, list);

        String fileName = "用户报表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";
        //处理中文下载名乱码
        try {
            fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition","attachment;filename="+fileName);
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void queryZ(){

    }

}

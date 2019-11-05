package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("code")
@Controller
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //获取验证码的内容
        String code= SecurityCode.getSecurityCode();
        //存入session
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        //获取图片
        BufferedImage image = SecurityImage.createImage(code);
        //设置响应类型
        response.setContentType("img/png");
        //将图片打印到前台
        ImageIO.write(image,"png",response.getOutputStream());
    }
    @RequestMapping("exit")
    public String  exit(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}

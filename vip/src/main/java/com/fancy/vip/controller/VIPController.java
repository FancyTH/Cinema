package com.fancy.vip.controller;


import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.server.HttpServerResponse;
import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class VIPController {
    //处理方法
    @RequestMapping("/haha")
    public String hello(Model model){
        //昨天项目返回数据，今天返回页面（准备一个模板页面视图）
        model.addAttribute("user","fancy");
        return "java";

    }

    //处理播放请求的方法
    @RequestMapping("/toPlay")
    public String toPlay(){
        //昨天项目返回数据，今天返回页面（准备一个模板页面视图）

        return "Play";
    }

    //处理播放请求的方法
    @RequestMapping("/play")
    public String play(String url, boolean isQR, HttpServletResponse response){
        if (isQR) {//判断复选框是否勾选，勾选后手机播放，生成二维码
            String playURL="https://jx.bozrc.com:4433/player/?url="+url;
            //把路径生成到图片中，利用工具类QRcodeUtil，在pom里面加的工具
            BufferedImage image= QrCodeUtil.generate(playURL,400,400);
            //把图片发送给浏览器，需要HttpServletResponse =》javaWeb
            try {
                ImageIO.write(image,"png",response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return "redirect:https://jx.bozrc.com:4433/player/?url="+url;//未勾选复选框在页面播放
        }
        return null;
    }



}

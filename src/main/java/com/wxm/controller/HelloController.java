package com.wxm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-23 11:20
 */
@Controller
public class HelloController {


    /**
     * 简单版本
     */
    @RequestMapping("/hello")
    public String hello(HttpServletRequest req){
        req.setAttribute("msg","测试springmvc环境搭建成功！！");
        return "hello";
    }
    /**
     * springmvc正式版
     */
    @RequestMapping("/hello1")
    public ModelAndView hello1(HttpServletRequest req){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/hello1");
        modelAndView.addObject("msg","测试springmvc环境正式版搭建成功！！");
        return modelAndView;
    }

}





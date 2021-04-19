package com.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: HelloController
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/3/29 10:23
 * @Version 1.0
 **/
public class HelloController  implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","springmvc helloController");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}

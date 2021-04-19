package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: SysControllerInterceptor
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/4/15 17:18
 * @Version 1.0
 **/
public class SysControllerInterceptor implements HandlerInterceptor {


    //预处理  controller 执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("预处理  controller 执行前");
        return true;
    }


    //后处理方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(" controller 执行后，返回页面之前 。。。");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(" 拦截器1 最后执行 afterCompletion 。。。");
    }
}

package com.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: SysExceptionResolver
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/4/15 17:05
 * @Version 1.0
 **/
public class SysExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SysExecption sysExecption = null;
        if (e instanceof SysExecption) {
            sysExecption = (SysExecption) e;
        }else{
            sysExecption = new SysExecption("系统繁忙，请您稍后再试！");
        }

        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("errormsg",sysExecption.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}

package com.controller;

import com.exception.SysExecption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ExceptionController
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/4/15 16:53
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/testException")
public class ExceptionController {

    @RequestMapping(value = "/testException")
    public String testException() throws SysExecption {

        try {
            int result = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysExecption("service 处理 异常，。" +  e.getMessage());
        }

        return "success";
    }


    @RequestMapping(value = "/testInterceptor")
    public String testInterceptor()  {
        System.out.println("拦截后进入controller.....");
        return "success";
    }
}

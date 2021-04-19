package com.exception;

/**
 * @ClassName: SysExecption
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/4/15 16:48
 * @Version 1.0
 **/
public class SysExecption extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysExecption(String message) {
        this.message = message;
    }
}

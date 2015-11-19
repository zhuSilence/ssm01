package com.silence.exception;

/**
 * Created by zhuxiang on 2015/11/19.
 * Desc :自定义的系统异常类,针对预期的异常，需要在程序中抛出此类的异常
 */
public class CustomException extends Exception{

    /**
     * 异常信息
     */
    private String message;

    public CustomException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.silence.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhuxiang on 2015/11/19.
 * Desc :全局异常处理器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler 就是处理器映射器要执行的Handler对象
     * @param ex 就是抛出的异常
     * @return
     */
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object handler, Exception ex) {

        /**
         * 思路：
         * 解析出异常的类型
         * 如果是系统自定义的异常，直接取出异常，在错误页面显示
         * 如果不是系统自定义的异常，构造一个自定义的系统异常，异常信息为“未知错误”
         */
        CustomException customException = null;
        if(ex instanceof CustomException){
            customException = (CustomException)ex;
        }else {
            customException = new CustomException("未知错误");
        }
        //取出错误信息,并设置到error页面
        String message = customException.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",message);
        modelAndView.setViewName("error");

        return modelAndView;
    }
}

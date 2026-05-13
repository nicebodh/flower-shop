package com.example.springboot.exception;


import com.example.springboot.common.Result;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




@ControllerAdvice(basePackages="com.example.springboot.controller") //这里改成你自己的包的路径
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);


    // 统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result error(Exception e){
        log.error("异常信息：",e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody//返回json串
    public Result customError( ServiceException e){
        return Result.error(e.getCode(), e.getMsg());
    }
}
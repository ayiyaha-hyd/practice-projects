package com.hyd.base.handler;

import com.hyd.base.exception.GlobalException;
import com.hyd.base.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public Response error(Exception e){
        log.error(e.getMessage());
        return Response.failed().message("执行了全局异常处理...");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public Response error(ArithmeticException e){
        log.error(e.getMessage());
        return Response.failed().message("执行了ArithmeticException异常处理...");
    }

    public Response error(GlobalException e){
        log.error(e.getMsg());
        return Response.failed().code(e.getCode()).message(e.getMsg());
    }
}

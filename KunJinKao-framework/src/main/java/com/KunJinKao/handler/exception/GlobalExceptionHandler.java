package com.KunJinKao.handler.exception;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.enums.AppHttpCodeEnum;
import com.KunJinKao.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

 //对Controller进行增强,如果Controller中出现异常,则会走这个方法

@RestControllerAdvice
@Slf4j//打印日志,加了这个注解才能使用log
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult sysExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常! {}",e);
        //从异常对象中获取提示信息,封装ResponseResult对象返回给前端
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    //除了自定义的错误外其它交给这个来处理
    @ExceptionHandler(Exception.class)
    public ResponseResult sysExceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常! {}",e);
        //从异常对象中获取提示信息,封装ResponseResult对象返回给前端
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }

}

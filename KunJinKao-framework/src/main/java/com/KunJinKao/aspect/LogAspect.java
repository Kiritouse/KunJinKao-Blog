package com.KunJinKao.aspect;

import com.KunJinKao.annotation.SystemLog;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect //实现日志类,便于调试
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.KunJinKao.annotation.SystemLog)")
    public void pt() {

    }

    @Around("pt()")  //这个注解是AspectJ的注解,表示在方法执行之前和之后执行
    public Object printLog(ProceedingJoinPoint joinPoint) {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handAfter(ret);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
            //根据不同的系统获取当前系统的换行符并且拼接
        }
        return ret;

    }

    private void handAfter(Object ret) {
        //打印出参
        log.info("Response : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //调用子类特有的方法需要强转
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法上的注解对象,即添加了自定义注解的方法
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("=======Start=======");
    // 打印请求 URL
        log.info("URL : {}",request.getRequestURL());
    // 打印描述信息
        log.info("BusinessName : {}", systemLog.businessName());
    // 打印 Http method
        log.info("HTTP Method : {}",request.getMethod() );
    // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    // 打印请求的 IP
        log.info("IP : {}", request.getRemoteHost());
    // 打印请求入参
        //将数组类型进行序列化传入JSON
        log.info("Request Args : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    //获取对应方法的注解对象
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return  systemLog;
    }
}

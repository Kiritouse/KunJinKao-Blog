package com.KunJinKao.handler.security;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.enums.AppHttpCodeEnum;
import com.KunJinKao.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//认证处理器
@Component //加上注解放到Spring容器当中
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) throws IOException, ServletException {
        authenticationException.printStackTrace();

        ResponseResult result = null;
        if(authenticationException instanceof BadCredentialsException) {//如果是其它报错信息,则直接获取其对应的信息
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authenticationException.getMessage());
        }
        else if(authenticationException instanceof InsufficientAuthenticationException){//如果报错信息是权限不足
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        else{
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"认证或授权失败");
        }
        //错误信息响应给前端转换成json
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}

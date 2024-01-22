package com.KunJinKao.handler.security;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.enums.AppHttpCodeEnum;
import com.KunJinKao.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component//交给Spring容器管理
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //这两个类写完后一定要配置给SecurityConfig
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端转换成json
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}

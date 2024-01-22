package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.User;
import com.KunJinKao.enums.AppHttpCodeEnum;
import com.KunJinKao.exception.SystemException;
import com.KunJinKao.service.BlogLoginService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if(!StringUtils.hasText(user.getUserName())){//前后端都要校验一致,防止有人直接向后端发布请求
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);//自定义异常

        }
        return blogLoginService.login(user);
    }
}

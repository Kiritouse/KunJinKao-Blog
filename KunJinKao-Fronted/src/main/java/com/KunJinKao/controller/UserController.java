package com.KunJinKao.controller;

import com.KunJinKao.annotation.SystemLog;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.User;
import com.KunJinKao.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags="用户相关接口",description = "暂定")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
       return userService.userInfo();
    }
    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")//自定义注解,用于记录日志
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }

}

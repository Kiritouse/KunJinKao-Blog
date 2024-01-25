package com.KunJinKao.service;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.User;

/**
 * @author 35238
 * @date 2023/7/22 0022 21:38
 */
public interface SystemLoginService {

    //登录
    ResponseResult login(User user);

}
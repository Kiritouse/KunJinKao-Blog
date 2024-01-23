package com.KunJinKao.service;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.User;

public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}

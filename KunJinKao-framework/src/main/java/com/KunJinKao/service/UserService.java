package com.KunJinKao.service;
import com.KunJinKao.domain.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.KunJinKao.domain.entity.User;
/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-01-23 11:02:10
 */
public interface UserService extends IService<User> {
     ResponseResult updateUserInfo(User user);

    ResponseResult userInfo();
}


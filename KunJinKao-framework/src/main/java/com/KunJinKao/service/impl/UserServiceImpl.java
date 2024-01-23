package com.KunJinKao.service.impl;

import com.KunJinKao.domain.entity.User;
import com.KunJinKao.mapper.UserMapper;
import com.KunJinKao.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-01-23 11:02:12
 */
@Service("userService")
    public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}


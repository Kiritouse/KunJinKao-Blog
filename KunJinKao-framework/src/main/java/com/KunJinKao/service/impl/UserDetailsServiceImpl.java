package com.KunJinKao.service.impl;

import com.KunJinKao.domain.entity.LoginUser;
import com.KunJinKao.domain.entity.User;
import com.KunJinKao.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override //实现接口后Security就会使用这个类来进行校验,否则默认是在内存中进行,我们需要在数据库中
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);//根据传进来的用户名进行等值匹配
        User user = userMapper.selectOne(queryWrapper);//根据自己构造的条件进行选择
        //判断是否查到用户,如果没有查到,抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        // TODO 查询权限信息封装

        return new LoginUser(user);//将对应的用户信息存储到LoginUser里面
    }
}

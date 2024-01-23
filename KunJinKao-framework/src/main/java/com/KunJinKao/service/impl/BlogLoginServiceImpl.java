package com.KunJinKao.service.impl;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.LoginUser;
import com.KunJinKao.domain.entity.User;
import com.KunJinKao.domain.vo.BlogUserLoginVo;
import com.KunJinKao.domain.vo.UserInfoVo;
import com.KunJinKao.service.BlogLoginService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.KunJinKao.utils.JwtUtil;
import com.KunJinKao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //获取userid,生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt =  JwtUtil.createJWT(userId);//将UserId进行加密
        //把用户信息存入redis,这个是redis的Key
        //Todo 这里可能会出现问题,似乎没有设置redis的key
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把token和userinfo封装成ResponseResult返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token,并且解析
       Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();//因为我们是将token请求放到Security类中
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid,可以从token中获取
      Long userId =  loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}

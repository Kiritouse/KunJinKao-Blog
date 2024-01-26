package com.KunJinKao.service;

import com.KunJinKao.domain.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.KunJinKao.domain.entity.Role;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/4 0004 13:33
 */
public interface RoleService extends IService<Role> {
    //查询用户的角色信息
    List<String> selectRoleKeyByUserId(Long id);


    //查询角色列表
    ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);

    //新增角色
    void insertRole(Role role);
}
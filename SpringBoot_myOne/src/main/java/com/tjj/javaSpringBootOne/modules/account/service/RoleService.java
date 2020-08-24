package com.tjj.javaSpringBootOne.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    PageInfo<Role> getRoleBySearchVo(SearchVo searchVo);
    Result<Role> insertRole(Role role);
    Result<Role> updateRole(Role role);
    Result<Object> deletRole(int roleId);

    Role getRoleByRoleId(int roleId);
}

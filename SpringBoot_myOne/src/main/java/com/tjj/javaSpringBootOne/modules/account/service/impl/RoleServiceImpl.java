package com.tjj.javaSpringBootOne.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.dao.RoleDao;
import com.tjj.javaSpringBootOne.modules.account.dao.RoleResourceDao;
import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.service.RoleService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Role> getRoleBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<Role>(Optional.ofNullable(roleDao.getRoleBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<Role> insertRole(Role role) {
        Role roleTemp=roleDao.getUserByRoleName(role.getRoleName());
        if(roleTemp!=null){
            return  new Result<Role>(Result.ResultStatus.FAILED.status,"Role name is repeat");
        }
        roleResourceDao.deleteRoleResourceByRoleId(role.getRoleId());
        List<Resource> resources=role.getResources();
        roleDao.inserRole(role);
        if (resources!=null && !resources.isEmpty()){
            resources.stream().forEach(item->{
                roleResourceDao.insertUserRole(role.getRoleId(),item.getResourceId());
            });
        }

        return new  Result<Role>(Result.ResultStatus.SUCCESS.status,"添加成功",role);
    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        Role roleTemp=roleDao.getUserByRoleName(role.getRoleName());
        if(roleTemp!=null &&roleTemp.getRoleId()!=role.getRoleId()){
            return  new Result<Role>(Result.ResultStatus.FAILED.status,"Role name is repeat");
        }
        roleDao.updateRole(role);
        roleResourceDao.deleteRoleResourceByRoleId(role.getRoleId());
        List<Resource> resources=role.getResources();
        if (resources!=null && ! resources.isEmpty()){
            resources.stream().forEach(item->{
                roleResourceDao.insertUserRole(role.getRoleId(),item.getResourceId());
            });
        }
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"update role success ");
    }


    @Override
    @Transactional
    public Result<Object> deletRole(int roleId) {
        roleDao.deleteRole(roleId);
        roleResourceDao.deleteRoleResourceByRoleId(roleId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delte user success");
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }
}

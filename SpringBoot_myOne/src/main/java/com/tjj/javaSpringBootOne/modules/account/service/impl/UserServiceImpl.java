package com.tjj.javaSpringBootOne.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.dao.UserDao;
import com.tjj.javaSpringBootOne.modules.account.dao.UserRoleDao;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private UserDao  userDao;
    @Autowired
     private UserRoleDao userRoleDao;
    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if(userTemp!=null){
            return  new Result<User>(Result.ResultStatus.FAILED.status,"User name is repeat");
        }
        user.setCreateDate(LocalDateTime.now());
        userDao.inserUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            roles.stream().forEach(item->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
        return new  Result<User>(Result.ResultStatus.SUCCESS.status,"添加成功",user);
    }

    @Override
    public Result<User> login(User user) {
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if(userTemp !=null&&userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
            return  new Result<User>(Result.ResultStatus.SUCCESS.status,"登录成功",user);
        }
        return new Result<>(Result.ResultStatus.FAILED.status,"密码错误");
    }

    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<User>(Optional.ofNullable(userDao.getUserBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if(userTemp!=null &&userTemp.getUserId()!=user.getUserId()){
            return  new Result<User>(Result.ResultStatus.FAILED.status,"User name is repeat");
        }
        userDao.updateUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            roles.stream().forEach(item->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"update user success ");
    }

    @Override
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delte user success");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }
}

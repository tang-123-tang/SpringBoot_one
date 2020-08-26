package com.tjj.javaSpringBootOne.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.config.ResourceBeanCofig;
import com.tjj.javaSpringBootOne.modules.account.dao.UserDao;
import com.tjj.javaSpringBootOne.modules.account.dao.UserRoleDao;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    ResourceBeanCofig resourceBeanCofig;
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
    /*    User userTemp=userDao.getUserByUserName(user.getUserName());
        if(userTemp !=null&&userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
            return  new Result<User>(Result.ResultStatus.SUCCESS.status,"登录成功",userTemp);
        }
        return new Result<>(Result.ResultStatus.FAILED.status,"密码错误");*/
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getUserName(),MD5Util.getMD5(user.getPassword()));
        usernamePasswordToken.setRememberMe(user.getRememberMe());
        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        }catch (Exception e){
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILED.status,"UserName or password is error.");
        }
        Session session = subject.getSession();
        session.setAttribute("adminUser",(User)subject.getPrincipal());
        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "Login success.", user);
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

    @Override
    public Result<String> uploadFile(MultipartFile multipartFile) {
        if(multipartFile.isEmpty())
        {
            return new Result<String>(
                    Result.ResultStatus.FAILED.status, "Please select img.");
        }
        String relativePath="";
        String destFile="";
        try {
        String osName=System.getProperty("os.name");
        if(osName.toLowerCase().startsWith("win")){
          destFile=resourceBeanCofig.getLocationPathForWindows()+multipartFile.getOriginalFilename();
        }else{
            destFile=resourceBeanCofig.getLocationPathForLinux()+multipartFile.getOriginalFilename();
        }
        relativePath=resourceBeanCofig.getRelativePath()+multipartFile.getOriginalFilename();
        File file=new File(destFile);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILED.status, "Upload failed.");
        }
        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    public Result<User> updateProfile(User user) {
        User user1=userDao.getUserByUserName(user.getUserName());
        if(user1!=null&&user1.getUserId()!=user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILED.status, "User name is repeat.");
        }
        userDao.updateUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Edit success.", user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void logout() {
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        Session session=subject.getSession();
        session.setAttribute("adminUser",null);
    }
}

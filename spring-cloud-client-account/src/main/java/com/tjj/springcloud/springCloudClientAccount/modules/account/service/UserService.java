package com.tjj.springcloud.springCloudClientAccount.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.tjj.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.tjj.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.tjj.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    Result<User> insertUser(User user);
    Result<User> login(User user);
    PageInfo<User> getUserBySearchVo(SearchVo searchVo);
    Result<User> updateUser(User user);
    Result<Object> deleteUser(int userId);
    User getUserByUserId(int userId);
    Result<String> uploadFile(MultipartFile multipartFile);

    Result<User> updateProfile(User user);

    User getUserByUserName(String userName);

    void logout();
}

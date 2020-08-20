package com.tjj.javaSpringBootOne.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;

public interface UserService {
    Result<User> insertUser(User user);
    Result<User> login(User user);
    PageInfo<User> getUserBySearchVo(SearchVo searchVo);
}

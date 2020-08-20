package com.tjj.javaSpringBootOne.modules.account.service;

import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;

public interface UserService {
    Result<User> insertUser(User user);
    Result<User> login(User user);
}

package com.tjj.javaSpringBootOne.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 127.0.0.1/api/user---post
     * {"userName":"admin","password":"111111"}
     */
   @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 127.0.0.1/api/login
     * {"userName":"admin","password":"123"}
     */
   @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<User> login(@RequestBody User user){
       return userService.login(user);
    }

    /**
     * 127.0.0.1/api/users ---post
     * {"currentPage":"1","pageSize":"5","keyWord":"hu"}
     * @param searchVo
     * @return
     */
    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo) {
       return userService.getUserBySearchVo(searchVo);
    }

}

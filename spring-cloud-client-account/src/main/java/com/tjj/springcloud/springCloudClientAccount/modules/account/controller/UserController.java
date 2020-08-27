package com.tjj.springcloud.springCloudClientAccount.modules.account.controller;

import com.github.pagehelper.PageInfo;

import com.tjj.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.tjj.springcloud.springCloudClientAccount.modules.account.service.UserService;
import com.tjj.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.tjj.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;


    /**
     * 127.0.0.1/api/user/1
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }
    @PostMapping(value = "/userImg",consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file){

        return userService.uploadFile(file);
    }


}

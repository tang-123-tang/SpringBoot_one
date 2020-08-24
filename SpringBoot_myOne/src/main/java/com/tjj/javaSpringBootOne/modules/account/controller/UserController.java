package com.tjj.javaSpringBootOne.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    Result<User> login(@RequestBody User user, HttpSession httpSession){
       Result<User> result=userService.login(user);
       httpSession.setAttribute("adminUser",result.getResults());
       return result;
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

    /**
     * 127.0.0.1/api/user  ----put
     * {"userName":"admin7","userImg":"/aaaa.jpg","userId":"12"}
     * @param user
     * @return
     */
    @PutMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 127.0.0.1/api/user/12
     * @param userId
     * @return
     */
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

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
    @PutMapping("/profile")
    public Result<User> updateProfile(@RequestBody User user){
        return userService.updateProfile(user);
    }

}

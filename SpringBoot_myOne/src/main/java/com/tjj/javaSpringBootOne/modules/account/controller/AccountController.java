package com.tjj.javaSpringBootOne.modules.account.controller;

import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController  {
    @Autowired
    UserService userService;
    /**
     * 127.0.0.1/account/users -----get
     * @return
     */
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }

    @GetMapping("/roles")
    public String rolesPage(){
        return "index";
    }
    @GetMapping("/resources")
    public String resourcesPage(){
        return "index";
    }

    /**
     *127.0.0.1/account/login   ----get
     */
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }
    @GetMapping("/registerVue")
    public String registerVuePage(){
        return "indexSimple";
    }

    @GetMapping("/profile")
    public String profilePage(){
        return "index";
    }

    /**
     * /account/logout
     * @param modelMap
     * @return
     */
    @GetMapping("/logout")
    public String logout(ModelMap modelMap){
        userService.logout();
        modelMap.addAttribute("tempate","/account/login");
        return "indexSimple";
    }
}

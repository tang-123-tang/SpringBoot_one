package com.tjj.javaSpringBootOne.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController  {
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

    /**
     * 127.0.0.1/account/users
     * @return
     */


}

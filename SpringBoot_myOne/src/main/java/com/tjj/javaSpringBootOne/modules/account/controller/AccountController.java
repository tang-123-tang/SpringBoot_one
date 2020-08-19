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



}

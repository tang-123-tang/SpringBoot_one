package com.tjj.javaSpringBootOne.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TextController {
    /**
     * 127.0.0.1:8080/test/test/testDesc
     * @return
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(){
        return "that was my testDesc";
    }
}

package com.tjj.javaSpringBootOne.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
    /**
     * 127.0.0.1/common/dashboard  ----get
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboardPage(){
        return "index";
    }

    /**
     * 127.0.0.1/common/403
     * @return
     */
    @GetMapping("/403")
    public String errorPage403(){
        return "index";
    }


}

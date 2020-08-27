package com.tjj.javaSpringBootOne.modules.common.controller;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.protocol.http.AuthenticationInfo;

import javax.security.sasl.AuthenticationException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErroHanlderController {
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> pageError(){
        return new Result<String>(Result.ResultStatus.FAILED.status,"/common/403");
    }
}

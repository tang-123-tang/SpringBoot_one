package com.tjj.javaSpringBootOne.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);
    private HttpServletRequest request;

    /**
      ^ ^
     -- --
     */
    @Pointcut("execution(public  * com.tjj.javaSpringBootOne.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){
    }
    @Before(value="com.tjj.javaSpringBootOne.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("=======This is before Controller");
      ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER . debug("请求来源:"+ request. getRemoteAddr());
                LOGGER. debug("请求URL :"
                        + request.getRequestURL(). toString());
        LOGGER. debug("请求方式: " + request. getMethod());
        LOGGER. debug("响应方法: " + joinPoint . getSignature() . getDeclaringTypeName() + "."+
                joinPoint. getSignature(). getName());
        LOGGER. debug("请求参数:"+ Arrays.toString(joinPoint .getArgs()));

    }
    @Around(value="com.tjj.javaSpringBootOne.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("=======This is around Controller====");
            return  proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
    @After(value="com.tjj.javaSpringBootOne.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("==========This is after Controller");
    }
}

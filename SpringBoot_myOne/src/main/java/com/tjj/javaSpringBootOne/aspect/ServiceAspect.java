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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(com.tjj.javaSpringBootOne.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}
    @Before(value="com.tjj.javaSpringBootOne.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinPoint){
        LOGGER.debug("=======This is before Service");
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
    @Around(value="com.tjj.javaSpringBootOne.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("=======This is around Service====");
        return  proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
    @After(value="com.tjj.javaSpringBootOne.aspect.ServiceAspect.servicePointCut()")
    public void afterService(){
        LOGGER.debug("==========This is after Service");
    }
}

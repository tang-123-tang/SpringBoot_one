package com.tjj.javaSpringBootOne.inteceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component//设置为系统bean
public class RequestViewInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER= LoggerFactory.getLogger(RequestViewInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       LOGGER.debug("============pre interceptor");
       return HandlerInterceptor.super.preHandle(request,response,handler);
        //return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("============post interceptor");
        if (modelAndView==null||modelAndView.getViewName().startsWith("redirect")){
            return;
        }
        String path=request.getServletPath();//获取path路径
        String template= (String) modelAndView.getModelMap().get("tempate");
        if (StringUtils.isBlank(template)){
            if(path.startsWith("/")){
                path=path.substring(1);
            }
            modelAndView.getModelMap().addAttribute("tempate",path.toLowerCase());
        }
         HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("============after interceptor");
        HandlerInterceptor.super.preHandle(request,response,handler);
    }
}

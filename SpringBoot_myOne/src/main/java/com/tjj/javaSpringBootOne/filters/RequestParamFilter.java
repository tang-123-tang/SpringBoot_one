package com.tjj.javaSpringBootOne.filters;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebFilter(filterName = "requestParamFilter",urlPatterns = "/**") //
//@Order(1) 当有多个过滤器时，用order设置优先级别
public class RequestParamFilter implements Filter {
    private static final Logger LOGGER= LoggerFactory.getLogger(RequestParamFilter.class);
    @Override//初始化
    public void init(FilterConfig filterConfig) throws ServletException {
    LOGGER.debug("========Init Request Param Fileter============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     LOGGER.debug("============Do request param Filter");
        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
 /*       Map<String, String[]> parameterMap = httpRequest.getParameterMap();
        parameterMap.put("paramKey",new String[]{"*****"});*/
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpRequest){
            @Override
            public String getParameter(String name) {
                String value=httpRequest.getParameter(name);
                if(StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
               String[] values=httpRequest.getParameterValues(name);
               if(values!=null&&values.length>0){
                   for (int i=0;i<values.length;i++) {
                       values[i]=values[i].replaceAll("fuck","***");
                   } return values;
               }
                 return super.getParameterValues(name);
            }
        };

        //todo 业务逻辑还未完成
        filterChain.doFilter(wrapper,servletResponse);

    }

    @Override//销毁
    public void destroy() {
        LOGGER.debug("========Dstroy Request Param Fileter============");

    }
}

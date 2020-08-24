package com.tjj.javaSpringBootOne.config;

import com.tjj.javaSpringBootOne.filters.RequestParamFilter;
import com.tjj.javaSpringBootOne.inteceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//代表是配置类
@AutoConfigureAfter({WebMvcAutoConfiguration.class})//把配置类放在sm之后运行
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${server.http.port}")
    private int port;
    @Autowired
    ResourceBeanCofig resourceBeanCofig;
    @Autowired
    RequestViewInterceptor requestViewInterceptor;
    @Bean
    public Connector connector(){//创建连接器
        Connector connector=new Connector();
        connector.setPort(port);//设置端口，引入HTTP的
        connector.setScheme("http");//设置网页开头
        return connector;
    }
    @Bean
    public ServletWebServerFactory WebServerFactory(){
        TomcatServletWebServerFactory tomcatServlet=new TomcatServletWebServerFactory();
       tomcatServlet.addAdditionalTomcatConnectors(connector());//添加连接器
        return  tomcatServlet;
    }
    @Bean
    public FilterRegistrationBean<RequestParamFilter> register(){
        FilterRegistrationBean<RequestParamFilter> register=
                new FilterRegistrationBean<RequestParamFilter>();
        register.setFilter(new RequestParamFilter());
        return register;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            //addResourceHandler相对  addResourceLocations绝对  ResourceUtils.FILE_URL_PREFIX前缀
            registry.addResourceHandler(resourceBeanCofig.getRelativePathPattern()).addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceBeanCofig.getLocationPathForWindows());
        }else{
            registry.addResourceHandler(resourceBeanCofig.getRelativePathPattern()).addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceBeanCofig.getLocationPathForLinux());
        }
    }
}

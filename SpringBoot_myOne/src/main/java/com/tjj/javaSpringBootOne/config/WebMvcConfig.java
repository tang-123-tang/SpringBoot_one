package com.tjj.javaSpringBootOne.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//代表是配置类
@AutoConfigureAfter({WebMvcAutoConfiguration.class})//把配置类放在sm之后运行
public class WebMvcConfig {
    @Value("${server.http.port}")
    private int port;
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
}

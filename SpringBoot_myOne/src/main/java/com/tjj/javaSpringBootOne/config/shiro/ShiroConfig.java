package com.tjj.javaSpringBootOne.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Component
public class ShiroConfig {
    @Autowired
    MyRealm myRealm;
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        //添加记住我功能
        manager.setRememberMeManager(rememberMeManager());
        //添加session管理
        manager.setSessionManager(sessionManager());
        return manager;
    }
    /**
     * 配置shiro过滤器工厂
     * -----------------
     * 拦截权限
     * anon：匿名访问，无需登录
     * authc：登录后才能访问
     * user：登录过能访问
     * logout：登出
     * roles：角色过滤器
     * ------------------
     * URL匹配风格
     * ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/
     * *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1
     * **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     * -----------------------
     * 方法名不能乱写，如果我们定义为别的名称，又没有添加注册过滤器的配置，那么shiro会加载ShiroWebFilterConfiguration过滤器，
     * 该过滤器会寻找shiroFilterFactoryBean，找不到会抛出异常
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());

        shiroFilter.setLoginUrl("/account/login");
        shiroFilter.setSuccessUrl("/account/dashboard");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "anon");
        map.put("/static/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/plugin/**", "anon");
        map.put("/account/login", "anon");
        map.put("/api/login", "anon");
        map.put("/account/register", "anon");
        map.put("/api/user", "anon");

        map.put("/common/**", "user");
        map.put("/test/**", "user");
        map.put("/account/**", "authc");

        shiroFilter.setFilterChainDefinitionMap(map);

        return shiroFilter;
    }

    /**
     * 注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie=new SimpleCookie("rememberMe");
        //如果httpOnly设置为true，则客户端不会暴露给客户脚本代码 cookie有助于减少某些类型的跨点站脚本攻击
        simpleCookie.setHttpOnly(true);
        //记住cookie生效时间，单位秒
        simpleCookie.setMaxAge(1*24*60*60);
        return simpleCookie;
    }
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherKey= Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherService(new AesCipherService());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }
/**
 * sessionCookie
 */
  @Bean
  public SimpleCookie sessionCookie(){
      SimpleCookie simpleCookie=new SimpleCookie("shiro.session");
      simpleCookie.setPath("/");
      simpleCookie.setHttpOnly(true);
      simpleCookie.setMaxAge(1*24*60*60);
      return simpleCookie;
  }
    public DefaultWebSessionManager sessionManager(){
      DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
      defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
      defaultWebSessionManager.setSessionIdCookie(sessionCookie());
      return defaultWebSessionManager;
    }
}

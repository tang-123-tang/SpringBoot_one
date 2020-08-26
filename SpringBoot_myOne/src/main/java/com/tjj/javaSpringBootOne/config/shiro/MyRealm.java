package com.tjj.javaSpringBootOne.config.shiro;

import com.tjj.javaSpringBootOne.modules.account.dao.UserDao;
import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.ResourceService;
import com.tjj.javaSpringBootOne.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        User user=(User)principalCollection.getPrimaryPrincipal();
        List<Role> roles=user.getRoles();
        if(roles !=null&& !roles.isEmpty()){
            roles.stream().forEach(item->{
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources=resourceService.getresourceByRoled(item.getRoleId());
                if(resources!=null&& !resources.isEmpty()){
                    resources.stream().forEach(resource->{
                        simpleAuthorizationInfo.addRole(resource.getPermission());
                    });
                }
            });
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName=(String)authenticationToken.getPrincipal();
        User user=userService.getUserByUserName(userName);
        if(user==null){
            throw new UnknownAccountException("The account do not exit .");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());//返回身份验证器
    }
}

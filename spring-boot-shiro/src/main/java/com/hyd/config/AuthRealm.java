package com.hyd.config;

import com.hyd.entity.User;
import com.hyd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 进行权限信息验证<br/>
 * 继承AuthorizingRealm抽象类，实现方法<br/>
 * Realm 本质上是一个特定的安全 DAO，它封装与数据源连接的细节，得到Shiro 所需的相关的数据<br/>
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权访问控制 Authorization
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("执行 doGetAuthorizationInfo 授权访问控制 方法...");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole());
        Set<String> permissions = new HashSet<>();
        permissions.add(user.getPermission());
        authorizationInfo.addRoles(roles);
        //addStringPermission和setStringPermissions区别在于前者是添加
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addStringPermission("user:info");

        return authorizationInfo;
    }

    /**
     * 验证用户身份 Authentication
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("执行 doGetAuthenticationInfo 身份认证 方法... ");
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = (String) authenticationToken.getPrincipal();

        //从数据库中获取用户
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //此处Shiro为我们做了密码验证，将token中的密码与数据库查询出的密码做比对
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,//用户名,此处对应doGetAuthorizationInfo，以及全局用户对象
                user.getPassword(),//密码
                getName());
        return authenticationInfo;
    }
}

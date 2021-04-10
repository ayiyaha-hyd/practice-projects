package com.hyd.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 进行Shiro配置<br/>
 * 主要配置三个Bean:<br/>
 * Realm实例 提供自定义的AuthorizingRealm权限认证实现类<br/>
 * SecurityManager实例 在SecurityManager中配置Realm<br/>
 * ShiroFilterFactoryBean实例 在ShiroFilterFactoryBean中指定登录登出路径及路径拦截规则<br/>
 */
@Configuration
public class ShiroConfig {

    @Bean
    AuthRealm authRealm(){
        AuthRealm authRealm = new AuthRealm();
//        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }

    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置权限认证
        securityManager.setRealm(authRealm());
        return securityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置Shiro安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        //配置访问权限
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/user/userInfo","perms[user:info]");
        filterChainDefinitionMap.put("/user/userAdd","perms[staff:add]");
        filterChainDefinitionMap.put("/user/userDel","perms[admin:del]");
        filterChainDefinitionMap.put("/user/**","authc");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * thymeleaf 扩展 Shiro
     */
    @Bean
    ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

/*    *//**
     * 哈希密码认证匹配（有问题）
     *//*
    @Bean
    HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constants.HASH_ALGORITHM);
        hashedCredentialsMatcher.setHashIterations(Constants.HASH_INTERATIONS);
        hashedCredentialsMatcher.setHashSalted(false);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }*/
}

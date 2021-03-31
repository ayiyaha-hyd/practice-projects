package com.hyd.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 方式二：
 * 通过配置类设置Security登录的用户名和密码
 * 编写配置类，继承WebSecurityConfigurerAdapter，重写父类configure方法
 */
//@Configuration//注释掉，暂不使用
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    /**
     * 配置
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//重写父类方法
        //创建密码解析器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //对密码进行加密
        String password = bCryptPasswordEncoder.encode("hyd");
        //配置用户名密码角色
        auth.inMemoryAuthentication().withUser("hyd").password(password).roles("admin");
    }

    /**
     * 创建密码编码器接口对象
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

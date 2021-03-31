package com.hyd.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//开启Spring Security注解，在controller层方法使用注解设置角色访问权限控制，写在配置类或者启动类上
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;//注入数据源

    @Bean
    public PersistentTokenRepository getTokenRepository(){//配置自动登录对象
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 认证设置
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义登录页面
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//自定义登录页面
                .loginPage("/login.html")//登录页面设置
                .loginProcessingUrl("/user/login")//登录访问路径
//                .defaultSuccessUrl("/test/success").permitAll()//登录成功跳转路径
                .defaultSuccessUrl("/success.html").permitAll()
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll()//设置那些路径可以直接访问，不需要设置认证
                //.antMatchers("/test/index").hasAuthority("admin")只能设置单一权限（角色）
                .antMatchers("/test/index").hasAnyAuthority("admin,manager")//设置多个权限（角色）
                .antMatchers("/test/index").hasAnyRole("sale")//设置角色，角色可以看成权限的集合，默认Spring Security加了前缀ROLE_，和hasAuthority基本没区别
                //.antMatchers("/test/index").hasAnyRole("sale,manager")和hasAnyAuthority类似
                .anyRequest().authenticated()

                .and().rememberMe().tokenRepository(getTokenRepository())//配置自动登录（记住我），配置操作数据库jdbc对象
                .tokenValiditySeconds(60)//设置有效时长（秒）
                .userDetailsService(userDetailsService)//设置校验userDetailService



                .and().csrf().disable();//关闭csrf防护

        http.exceptionHandling().accessDeniedPage("/unauth.html");//自定义没有权限403页面

        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/test/hello").permitAll();//注销退出页面
    }
}

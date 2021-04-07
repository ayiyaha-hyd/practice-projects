package com.hyd.security.config;

import com.hyd.security.filter.TokenAuthFilter;
import com.hyd.security.filter.TokenLoginFilter;
import com.hyd.security.security.DefaultPasswordEncoder;
import com.hyd.security.security.TokenLogoutHandler;
import com.hyd.security.security.TokenManager;
import com.hyd.security.security.UnAuthEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private UserDetailsService userDetailsService;

    public TokenWebSecurityConfig(TokenManager tokenManager,RedisTemplate redisTemplate,DefaultPasswordEncoder defaultPasswordEncoder,UserDetailsService userDetailsService){
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling()
                //传入自定义未授权处理类
                .authenticationEntryPoint(new UnAuthEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                //退出路径
                .and()
                .logout().logoutUrl("/admin/acl/index/logout")
                //退出登录处理器
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate))
                .and()
                //登录认证过滤器
                .addFilter(new TokenLoginFilter(tokenManager,redisTemplate,authenticationManager()))
                //权限过滤器
                .addFilter(new TokenAuthFilter(authenticationManager(),tokenManager,redisTemplate))
                .httpBasic();
    }

    //调用userDetailsService和密码处理
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    //不进行认证的路径，可以直接访问
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }

}

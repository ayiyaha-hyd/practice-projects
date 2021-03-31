package com.hyd.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyd.practice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义UserDetailsService接口实现类
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        //此处演示，自己构造了一个User对象返回回去，实际应该为查数据库获取用户名密码和角色
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("amdin");
        //使用Security默认的UserDetails接口实现类User返回对象
        return new User("hyd",new BCryptPasswordEncoder().encode("hyd"),authorities);
         */
        //查询数据库完成用户认证
        //调用userMapper方法，根据用户名查询数据库
        //创建条件构造器
        QueryWrapper<com.hyd.practice.entity.User> wrapper = new QueryWrapper<>();
        wrapper.eq("USERNAME",username);
        //查询数据库
        com.hyd.practice.entity.User user = userMapper.selectOne(wrapper);

        //判断
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_sale");
        //从数据库查询结果中得到用户名和密码
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),authorities);

    }
}

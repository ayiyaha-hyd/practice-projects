package com.hyd.rbac.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hyd.rbac.entity.User;
import com.hyd.rbac.service.PermissionService;
import com.hyd.rbac.service.UserService;
import com.hyd.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询数据库
        User user = userService.selectByUsername(username);

        //判断用户是否存在
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //复制对象属性
        com.hyd.security.entity.User sUser = new com.hyd.security.entity.User();
        BeanUtils.copyProperties(user,sUser);

        //根据用户查询用户权限列表
       List<JSONObject> permissionValueList =  permissionService.selectPermissionByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserInfo(sUser);
        securityUser.setPermissionValueList(permissionValueList);//存在问题

        return securityUser;
    }
}

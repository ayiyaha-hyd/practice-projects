package com.hyd.service.impl;

import com.hyd.entity.User;
import com.hyd.mapper.UserMapper;
import com.hyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}

package com.hyd.service;

import com.hyd.entity.User;

public interface UserService {
    public User findByUsername(String userName);
}

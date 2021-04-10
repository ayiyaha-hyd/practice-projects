package com.hyd.mapper;

import com.hyd.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User selectByUsername(String username);
}

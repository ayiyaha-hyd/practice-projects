package com.hyd.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyd.practice.entity.User;
import org.apache.ibatis.annotations.Mapper;

//@Repository//@repository则需要在Spring中配置扫描包地址，然后生成dao层的bean，之后被注入到ServiceImpl中
@Mapper//使用@mapper后，不需要在spring配置中设置扫描地址，通过mapper.xml里面的namespace属性对应相关的mapper类，spring将动态的生成Bean后注入到ServiceImpl中
public interface UserMapper extends BaseMapper<User> {
}

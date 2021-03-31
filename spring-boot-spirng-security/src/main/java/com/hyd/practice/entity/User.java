package com.hyd.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成get,set方法,equals,hashCode,toString方法默认有无参构造器，但如果加了@AllArgsConstructor注解则没有了无参构造器
@AllArgsConstructor//有参构造器
@NoArgsConstructor//无参构造器
public class User {
    private Integer id;
    private String username;
    private String password;
}

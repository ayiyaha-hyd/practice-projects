package com.hyd.practice.controller;

import com.hyd.practice.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/index")
    public String index(){
        return "hello index";
    }

    @GetMapping("/secured")
    @Secured({"ROLE_sale","ROLE_manager"})
    public String secured(){
        return "基于注解的角色访问控制，具有ROLE_sale,ROLE_manager角色可以访问此方法";
    }

    @GetMapping("/preAuthorize")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String preAuthorize(){
        return "preAuthorize方法调用成功，执行方法前调用，基于表达式计算来限制对方法的访问";
    }

    @GetMapping("/postAuthorize")
    @PostAuthorize("hasAnyAuthority('king')")
    public String postAuthorize(){
        System.out.println("方法执行后调用，允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常");
        return "调用postAuthorize方法成功";
    }

    @GetMapping("/postFilter")
    @PostFilter("filterObject.username == 'zhangsan'")
    public List<User> postFilter(){
       List<User> userList = new ArrayList<>();
       userList.add(new User(1,"zhangsan","123"));
       userList.add(new User(2,"xiaohong","456"));
        System.out.println("调用postFilter方法成功，允许方法调用,但必须按照表达式来过滤方法的结果");
       return userList;
    }
}

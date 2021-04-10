package com.hyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @RequestMapping("userAdd")
    public String userAdd(){
        return "userAdd";
    }

    @RequestMapping("userDel")
    public String userDel(){
        return "userDel";
    }
}


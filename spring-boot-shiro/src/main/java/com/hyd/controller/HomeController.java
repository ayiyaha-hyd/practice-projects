package com.hyd.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping({"/","index"})
    public String index(){
        return "/index";
    }

    @RequestMapping("/login")
    public String login(String username, String password){
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            user.login(token);
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",user);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            SecurityUtils.getSubject().getSession().setAttribute("msg",e.getMessage());
            return "/login";//如何获取当前request.设置sesion
        }
        return "/index";
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}

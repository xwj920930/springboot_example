package com.xwj.jwt.controller;

import com.xwj.jwt.constants.JwtConstant;
import com.xwj.jwt.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class LoginController {
    @GetMapping("/register")
    public String login(){
        return JwtUtil.creatJWT(JwtConstant.JWT_ID, "xwj", "hello world", 1000);
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

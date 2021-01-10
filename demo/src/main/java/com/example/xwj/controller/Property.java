package com.example.xwj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用@value读取配置文件
 */
@RestController
public class Property {
    @Value("${server.port}")
    private String port;

    @GetMapping("port")
    public String port(){
        return port;
    }
    
}

package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aopController")
public class AopController {
    @GetMapping("/method1")
    public void method1(){
        System.err.println("method1");
    }
    @GetMapping("/method2/{param}")
    public String method2(@PathVariable("param") String param){
        System.err.println("method2:" + param);
        return param;
    }
}

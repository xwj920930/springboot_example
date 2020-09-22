package com.example.publisher.controller;

import com.example.publisher.register.MyEventRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class MyEventController {
    @Autowired
    private MyEventRegister register;
    @GetMapping("registerUser")
    public void register(){
        register.register();
    }

    @GetMapping("registerUser2")
    public void register2(){
        register.register2();
    }
}

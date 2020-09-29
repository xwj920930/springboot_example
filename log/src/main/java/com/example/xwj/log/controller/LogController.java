package com.example.xwj.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    //不使用lombok的时候必需使用下属代码声明
    //private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/hello")
    public String hello(){
        log.info("test logging ...");
        return "hello";
    }
}

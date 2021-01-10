package com.example.xwj.controller;

import com.example.xwj.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用@value读取配置文件
 */
@RestController
public class Property2 {
    @Autowired
    private Student student;
    @GetMapping("student")
    public String student(){
        return student.toString();
    }
    
}

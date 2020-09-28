package com.example.async.controller;

import com.example.async.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/index")
    public void index(){
        System.err.println("###indexController###          1");
        indexService.getIndex();
        System.err.println("###indexController###          4");
    }
}

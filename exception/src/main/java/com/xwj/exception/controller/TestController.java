package com.xwj.exception.controller;

import com.xwj.exception.entity.Item;
import com.xwj.exception.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 典型的rest风格接口
 * @Author yuki
 * @Date 2019/4/25 9:17
 * @Version 1.0
 **/
@RestController
public class TestController {
    @Autowired
    ItemService itemService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/item")
    public ResponseEntity<Item> saveItem(Item item){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(item));
    }
}

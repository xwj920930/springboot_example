package com.example.xwj.service;

import org.springframework.stereotype.Service;

/**
 * @Description 用于单元测试demo
 * @Author yuki
 * @Date 2021/10/16 18:00
 * @Version 1.0
 **/
@Service
public class DemoService {
    public void exception(){
        throw new RuntimeException();
    }

    public int add(int a,int b){
        return Integer.sum(a,b);
    }
}

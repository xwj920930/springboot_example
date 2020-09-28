package com.example.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Async //开启子线程
    public void getIndex(){
        System.err.println("###getIndex###          2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("###getIndex###          3");
    }
}

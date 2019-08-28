package com.xwj.jacoco.service;

import org.springframework.stereotype.Service;

@Service
public class JacocoTest {
    public int getSum(Integer a,Integer b){
        return Math.addExact(a,b);
    }
}

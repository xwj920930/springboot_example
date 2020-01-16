package com.xwj.mockmvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/*
 *SpringRunner,MockitoJUnitRunner都行
 */

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MockTest {

    @Mock
    private List mockList;

    @Test
    public void mockBean(){
        //调用mock对象的方法
        mockList.add(1);
        //验证方法是否执行
        Mockito.verify(mockList).add(1);
    }

}
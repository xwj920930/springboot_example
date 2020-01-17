package com.xwj.mockmvc.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/*
 * @Mock注解的使用效果与Mockito一样
 */

@RunWith(SpringRunner.class) //初始化方式1
//@RunWith(MockitoJUnitRunner.class) //初始化方式2
@SpringBootTest
public class MockTest {

    @Mock
    private List<Integer> mockList;

    @Mock
    private UserController userController;

    //初始化方式3
//    public MockTest(){
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void mockTest(){
        //调用mock对象的方法
        mockList.add(1);
        //验证方法是否执行
        Mockito.verify(mockList).add(1);
    }

}
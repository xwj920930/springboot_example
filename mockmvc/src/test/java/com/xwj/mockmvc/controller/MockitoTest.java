package com.xwj.mockmvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/*
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTest {


    @Test
    public void mockBean(){
        //模拟创建一个List对象
        List<Integer> mock =  Mockito.mock(List.class);
        //调用mock对象的方法
        mock.add(1);
        mock.clear();
        //验证方法是否执行
        Mockito.verify(mock).add(1);
        Mockito.verify(mock).clear();
    }

}
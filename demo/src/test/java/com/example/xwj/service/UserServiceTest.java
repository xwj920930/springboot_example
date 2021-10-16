package com.example.xwj.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Description 单元测试
 * @Author yuki
 * @Date 2021/10/16 17:38
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private DemoService demoService;

    @Test
    public void assertTrue(){
        TestCase.assertTrue(true);
    }

    @Test(expected=RuntimeException.class)
    public void assertWithException(){
        demoService.exception();
        TestCase.fail();
    }

    @Test
    public void add(){
        int add = demoService.add(1, 2);
        TestCase.assertEquals(3,add);
    }
}

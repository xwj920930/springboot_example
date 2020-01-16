package com.xwj.mockmvc.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/*
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockBeanTest {

    @MockBean
    private UserController userController;

    //测试mockBean: 创建一个虚拟的controller,service替代那些暂未完成的方法
    // 在实际开发中，我们自己写的Controller、Service很可能去调用别的同事或别的项目组写的Service、Mapper
    // ，对方可能只写了一个接口，没有实现，这样是没法进行测试的。
    @Test
    public void mockBean() throws Exception {
        Mockito.when(userController.delete(1)).thenReturn("虚拟的删除");
        TestCase.assertEquals("虚拟的删除", userController.delete(1));
    }

}
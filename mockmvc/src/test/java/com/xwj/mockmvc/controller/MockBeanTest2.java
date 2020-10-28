package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.service.UserServiceDepend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * 当编写不需要从Spring Boot容器中获取任何依赖项的测试时，使用@Mock，它快速且有利于隔离测试组件。
 * 如果测试需要依赖于Spring Boot容器，并且您还想添加或模拟其中一个容器bean，使用@MockBean。
 * 如果需要使用MockBean注解,需要使用SpringRunner
 * @MockBean类似于简化版的@InjectMocks
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockBeanTest2 {

    @MockBean  //此处如果使用@Mock，返回的是实际值xwj而不是模拟值qqq(因为userService的调用链是实际的)
    private UserServiceDepend userServiceDepend;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mockDependentBean() throws Exception {
        Mockito.when(userServiceDepend.getName()).thenReturn("qqq");
        mockMvc.perform(get("/user/dependent2"))
                .andExpect(status().is(200))
                .andDo(print());
    }

}
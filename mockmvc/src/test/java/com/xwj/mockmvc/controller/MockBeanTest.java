package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.User;
import com.xwj.mockmvc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * 注意：@MockBean可以解决单元测试中的一些！依赖！问题，即容器中的类A需要调用/依赖容器中的类B
 * 它允许在Spring ApplicationContext中添加Mockito模拟.
 * 如果在上下文中存在与声明的类兼容的bean,则它将由mock替换它.
 * 如果不是这样,它会将上下文中的mock添加为bean.
 * 主要用来引入spring容器管理的bean，和@Mock有区别，常与@WebMvcTest联用
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockBeanTest {

    //此处用到的service是模拟值，是MockBean塞进了容器并调用
    @MockBean
    private UserService userService;

//    @Mock //此处用到的service是真实值
//    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mockBean() throws Exception {
        Mockito.when(userService.getUser()).thenReturn(new User(2, "qqq"));
        mockMvc.perform(get("/user/dependent"))
                .andExpect(status().is(200))
                .andDo(print());
    }

}
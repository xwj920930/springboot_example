package com.xwj.mockmvc.controller;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/*
 * 1、mockMvc.perform执行一个请求。
 * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
 * 3、ResultActions.param添加请求传值
 * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
 * 5、ResultActions.andExpect添加执行完成后的断言。
 * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
 *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
 * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
 * tips: @MockBean用来构造一个模拟类，Mockito用来测试类，MockMVC用来发请求
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserController userController;

    @Test
    public void list() throws Exception {
        post();
        mockMvc.perform(get("/user/list"))//get可以简写
                .andExpect(status().is(200))//status可以自定义
                .andDo(print());
    }

    @Test
    public void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/post")
                .param("id", "1")
                .param("name", "xwj1"))
                .andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        post();
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/del/1"))
                .andExpect(status().isOk());
    }

    //一些常用的测试

    //测试普通控制器
    @Test
    public void normal() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/hello")) //执行请求
                .andExpect(model().attributeExists("xwj")) //验证存储模型数据
                .andExpect(view().name("demo")) //验证viewName
                .andExpect(forwardedUrl("demo"))//验证视图渲染时forward到的页面
                .andExpect(handler().handlerType(UserController.class)) //验证执行的控制器类型
                .andExpect(status().isOk())//验证状态码，也可用于自定义异常验证
                .andExpect(handler().methodName("hello")) //验证执行的控制器方法名
                .andExpect(model().hasNoErrors()) //验证页面没有错误
//                .andExpect(flash().attributeExists("success")) //验证存在flash属性
                .andDo(print()); //输出MvcResult到控制台
    }

    //得到MvcResult自定义验证
    @Test
    public void define() throws Exception{
        MvcResult result = mockMvc.perform(get("/user/hello"))//执行请求
                .andReturn(); //返回MvcResult
        Assert.assertNotNull(result.getModelAndView().getModel().get("xwj")); //自定义断言
    }

    //文件上传
    @Test
    public void file() throws Exception {
        URI uri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("eolinker信息.xlsx")).toURI();
        InputStream stream = Files.newInputStream(Paths.get(uri));
        mockMvc.perform(multipart("/user/file")
                .file(new MockMultipartFile("file", "eolinker信息.xlsx", MediaType.MULTIPART_FORM_DATA_VALUE, stream)))
                .andExpect(status().isOk());
    }

    //JSON请求/响应验证
    @Test
    public void json() throws Exception {
        String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/json")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(requestBody)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)) //执行请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //验证响应contentType
                .andExpect(jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
    }

    //测试mockBean: 创建一个虚拟的controller,service替代那些暂未完成的方法
    // 在实际开发中，我们自己写的Controller、Service很可能去调用别的同事或别的项目组写的Service、Mapper
    // ，对方可能只写了一个接口，没有实现，这样是没法进行测试的。
    @Test
    public void mockBean() throws Exception {
        Mockito.when(userController.delete(1)).thenReturn("虚拟的删除");
        TestCase.assertEquals("虚拟的删除", userController.delete(1));
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/del/1"))
                .andExpect(status().isOk());
    }

    //异步测试，有问题，暂时不讨论
//    @Test
//    public void asyn() throws Exception{
//        //Callable
//        MvcResult result = mockMvc.perform(get("/user/asyn?id=1&name=zhang")) //执行请求
//                .andExpect(request().asyncStarted())
//                .andExpect(request().asyncResult(CoreMatchers.instanceOf(User.class))) //默认会等10秒超时
//                .andReturn();
//
//        mockMvc.perform(asyncDispatch(result))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1));
//    }
}
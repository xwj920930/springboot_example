package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
 * 1、mockMvc.perform执行一个请求。
 * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
 * 3、ResultActions.param添加请求传值
 * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
 * 5、ResultActions.andExpect添加执行完成后的断言。
 * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
 *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
 * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

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
                .andExpect(status().isOk())//验证状态码
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

    //文件上传 todo
    @Test
    public void file() throws Exception {
        URI uri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("eolinker信息.xlsx")).toURI();
        InputStream stream = Files.newInputStream(Paths.get(uri));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/user/file")
                .file(new MockMultipartFile("eolinker信息.xlsx",stream))) //执行文件上传
                .andExpect(view().name("success")); //验证视图
    }

    //JSON请求/响应验证
    public void json() throws Exception {
        String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                .accept(MediaType.APPLICATION_JSON)) //执行请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
                .andExpect(jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/

        String errorBody = "{id:1, name:zhang}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(errorBody)
                .accept(MediaType.APPLICATION_JSON)) //执行请求
                .andExpect(status().isBadRequest()) //400错误请求
                .andReturn();

        Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体
    }

    //异步测试
    public void asyn() throws Exception{
        //Callable
        MvcResult result = mockMvc.perform(get("/user/async1?id=1&name=zhang")) //执行请求
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(CoreMatchers.instanceOf(User.class))) //默认会等10秒超时
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    //全局配置
    public void config() throws Exception{
//        mockMvc = webAppContextSetup(wac)
//                .defaultRequest(get("/user/1").requestAttr("default", true)) //默认请求 如果其是Mergeable类型的，会自动合并的哦mockMvc.perform中的RequestBuilder
//                .alwaysDo(print())  //默认每次执行请求后都做的动作
//                .alwaysExpect(request().attribute("default", true)) //默认每次执行后进行验证的断言
//                .build();
//
//        mockMvc.perform(get("/user/1"))
//                .andExpect(model().attributeExists("user"));

    }
}
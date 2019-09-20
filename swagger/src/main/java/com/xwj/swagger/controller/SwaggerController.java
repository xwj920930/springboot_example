package com.xwj.swagger.controller;

import com.xwj.swagger.model.Demo;
import com.xwj.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 *tips:前台传key/value过来，后台直接用实体类接受
 *     前台传json过来，后台实体类接受前加@requestBody/或者@requestBody接受json再另做处理
 */

@Api(tags = "swagger测试")
@RestController
@RequestMapping("/test")
public class SwaggerController {
    @ApiOperation("测试无参get方法")
    @GetMapping("/get")
    public String get(){
        return "get success";
    }

    @ApiOperation("测试有参post方法")
    @ApiImplicitParam(value = "用户名",name = "name",required = true,paramType = "query",dataType = "String")
    @PostMapping("/post")
    public String post(@RequestParam("name") String name){
        return name;
    }

    @ApiOperation(value = "测试Object")//表单提交
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping("/object")
    public User object(User user){
        System.out.println(user);
        return user;
    }

    @ApiOperation(value = "测试jsonObject",produces="application/json",
            consumes="application/json",response = Demo.class)//json提交
    @PostMapping("/json")
    public Demo json(@RequestBody Demo demo){
        System.out.println(demo);
        return demo;
    }
}

package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.User;
import com.xwj.mockmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
@RequestMapping("/user")
public class UserController {

    // 创建线程安全的map
    private static Map<Integer, User> users = new ConcurrentHashMap<>();
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     */
    @GetMapping(value = "/list")
    @ResponseBody
    public List<User> list(){
        List<User> userList = new ArrayList<>(users.values());
        System.out.println("UserController.list");
        UserController.getMap();
        return userList;
    }

    /**
     * 添加用户
     */
    @PostMapping("/post")
    @ResponseBody
    public String post(User user){
        users.put(user.getId(),user);
        System.out.println("UserController.post");
        UserController.getMap();
        return "success";
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/del/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id){
        users.remove(id);
        System.out.println("UserController.delete");
        UserController.getMap();
        return "success";
    }

    private static void getMap(){
        users.forEach((integer, user) -> System.out.println(user));
    }

    @GetMapping(value = "/hello")
    public ModelAndView hello(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("xwj","xwj");
        modelAndView.setViewName("demo");
        return modelAndView;
    }

    @PostMapping("/file")
    @ResponseBody
    public String file(@RequestParam("file")MultipartFile multipartFile){
        return "upload file success";
    }

    @PostMapping(value = "/json",produces = "application/json;charset=UTF-8")
    @ResponseBody//也可以使用@RequestBody alibaba.JSONObject jsonObject接收，但没必要
    public User jsonObject(@RequestBody User user){
        return user;
    }

//    @GetMapping(value = "/asyn")
//    @ResponseBody//get?a=a&b=b也可直接使用User user接收
//    public User asyn(@RequestParam("id") Integer id,@RequestParam("name") String name){
//        User user = new User(id,name);
//        return user;
//    }
    /**
     * 测试依赖问题
     */
    @GetMapping(value = "/dependent")
    @ResponseBody
    public User dependent(){
        User user = userService.getUser();
        System.out.println("UserController.dependent："+user);
        return user;
    }

    /**
     * 测试依赖问题
     */
    @GetMapping(value = "/dependent2")
    @ResponseBody
    public String dependent2(){
        String dependName = userService.getDependName();
        System.out.println("UserController.dependent2: "+dependName);
        return dependName;
    }
}
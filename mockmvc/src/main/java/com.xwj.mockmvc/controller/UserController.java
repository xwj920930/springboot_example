package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    // 创建线程安全的map
    private static Map<Integer, User> users = new ConcurrentHashMap<>();

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
}
package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    // 创建线程安全的map
    private static Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * 查询用户列表
     */
    @GetMapping(value = "/list")
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
    public String delete(@PathVariable Integer id){
        users.remove(id);
        System.out.println("UserController.delete");
        UserController.getMap();
        return "success";
    }

    private static void getMap(){
        users.forEach((integer, user) -> System.out.println(user));
    }

}
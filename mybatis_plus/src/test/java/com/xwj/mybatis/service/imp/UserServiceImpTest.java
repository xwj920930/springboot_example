package com.xwj.mybatis.service.imp;

import com.xwj.mybatis.pojo.User;
import com.xwj.mybatis.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImpTest {
    private User user;
    private List<User> users=new ArrayList<>();
    @Autowired
    UserService userService;

    @Before
    public void initUser(){
        user=new User();
        user.setUserName("xwj1");
        user.setAge(27);
        users.add(user);
        user=new User();
        user.setUserName("xwj2");
        user.setAge(28);
        users.add(user);
    }

    @Test
    public void insertUser() {
        Assert.assertEquals(userService.insertUser(user),1);
    }

    @Test
    public void insertUsers() {
        Assert.assertTrue(userService.insertUsers(users));
    }

    @Test
    public void selectByAge() {
        User user = userService.selectByAge(26);
        System.out.println(user);
    }

    @Test
    public void selectAll() {
        Assert.assertEquals(userService.selectAll().size(),3);
    }

    @Test
    public void selectByPage1() {
        System.out.println(userService.selectByPage1(1).getRecords());
        Assert.assertTrue(true);
    }

    @Test
    public void selectByPage2() {
        System.out.println(userService.selectByPage2(1).getRecords());
        Assert.assertTrue(true);
    }
}
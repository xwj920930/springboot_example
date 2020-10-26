package com.example.xwj.mongodb.service;

import com.example.xwj.mongodb.entity.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest extends TestCase {
    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        userService.insert(new User(1, 28, "xwj"));
    }
    @Test
    public void testUpdate() {
        userService.update(new User(1, 28, "xwj123"));
    }
    @Test
    public void testQueryByName() {
        User xwj123 = userService.queryByName("xwj123");
        TestCase.assertTrue(xwj123.toString().contains("xwj123"));
    }
    @Test
    public void testDeleteById() {
        userService.deleteById(1);
    }
}
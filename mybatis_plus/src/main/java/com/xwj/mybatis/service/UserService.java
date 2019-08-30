package com.xwj.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xwj.mybatis.pojo.User;

import java.util.List;

public interface UserService{
    int insertUser(User user);
    boolean insertUsers(List<User> users);
    User selectByAge(int age);
    List<User> selectAll();
    IPage<User> selectByPage1(int page);
    IPage<User> selectByPage2(int page);
}

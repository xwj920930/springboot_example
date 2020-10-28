package com.xwj.mockmvc.service;

import com.xwj.mockmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    UserServiceDepend userServiceDepend;

    public User getUser(){
        return new User(1, "xwj");
    }

    public User exception(){
        return null;
    }

    public String getName(){
        return "xwj";
    }

    public void setName() {getUser().setName("123");}

    public String getDependName(){
        return userServiceDepend.getName();
    }
}

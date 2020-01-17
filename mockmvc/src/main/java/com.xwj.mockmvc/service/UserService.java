package com.xwj.mockmvc.service;

import com.xwj.mockmvc.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    public User getUser(){
        return new User(1, "xwj");
    }

    public User exception(){
        return null;
    }

    public String getName(){
        return "xwj";
    }
}

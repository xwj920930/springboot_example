package com.example.cache.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 要缓存的类需要序列化
 */
@Data
public class CacheUser implements Serializable{
    private String userId;
    private String userName;
    private int age;

    @Override
    public String toString() {
        return "CacheUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public CacheUser(String userId) {
        this.userId = userId;
    }
}

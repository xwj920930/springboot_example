package com.xwj.jwt.utils;

import com.xwj.jwt.constants.JwtConstant;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilTest {
    @Test
    public void creatJWT() {
        String s = JwtUtil.creatJWT(JwtConstant.JWT_ID, "xwj", "hahaha", JwtConstant.JWT_TTL);
        System.out.println(s);
    }
    @Test
    public void parseJWT(){
        Claims c = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjM0NTYiLCJzdWIiOiJoYWhhaGEiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImlzcyI6Inh3aiIsImV4cCI6MTU3MTEyNDQ3NiwiaWF0IjoxNTcxMTIwODc2LCJqdGkiOiI2OWU1NDQyYy1mNDMwLTQ4MmItOWIzNi1kZjEyZDBlMGFkODYifQ.67EAOu-5GXImkUZH_YLESoa__j59g-nEe-6GytxJ3es");
        System.out.println(c.getId());
        System.out.println(c.getIssuedAt());
        System.out.println(c.getSubject());
        System.out.println(c.getIssuer());
        System.out.println(c.get("uid", String.class));
    }
}
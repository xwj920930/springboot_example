package com.xwj.jwt.constants;


import java.util.UUID;

public class JwtConstant {
    public static final String JWT_ID = UUID.randomUUID().toString();
    //密文
    public static final String JWT_SECRET = "xwj";
    public static final int JWT_TTL = 1000*60*60;
}

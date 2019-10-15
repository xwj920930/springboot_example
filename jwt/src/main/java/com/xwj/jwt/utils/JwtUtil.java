package com.xwj.jwt.utils;


import com.xwj.jwt.constants.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //生成秘钥
    private static SecretKey generateKey(){
        byte[] decode = Base64Utils.decodeFromString(JwtConstant.JWT_SECRET);
        return new SecretKeySpec(decode, 0, decode.length, "AES");
    }

    /**
     * 加密
     * @param id 唯一标识，token
     * @param issuer jwt颁发人
     * @param subject jwt传递主体
     * @param ttlMillis 过期时间（毫秒）
     * @return 生成的jwt字符串
     */
    public static String creatJWT(String id,String issuer,String subject,long ttlMillis){
        //指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long currentTimeMillis = System.currentTimeMillis();
        Date now =new Date(currentTimeMillis);

        //创建payload的私有声明（根据特定的业务需要添加）
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", "123456");
        claims.put("user_name", "admin");

        //生成签名的时候使用的秘钥secret，这个秘钥不能外露
        SecretKey key = generateKey();

        //为payload添加各种标准声明和私有声明
        JwtBuilder jwtBuilder = Jwts.builder()//设置jwt的body
                .setClaims(claims)//私有声明要先设置,写在标准的声明后就覆盖了标准的声明的
                .setId(id)//设置jti(JWT ID)：是JWT的唯一标识，这个可以设置为一个不重复的值
                .setIssuedAt(now)//iat: jwt的签发时间
                .setIssuer(issuer)//issuer：jwt签发人
                .setSubject(subject)//sub(Subject)：代表这个JWT的主体，这个是一个json格式的字符串
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥

        // 设置过期时间,注意：如果jwt已经过期了，会抛出io.jsonwebtoken.ExpiredJwtException
        if (ttlMillis >= 0) {
            long expMillis = currentTimeMillis + ttlMillis;
            jwtBuilder.setExpiration(new Date(expMillis));
        }
        return jwtBuilder.compact();
    }
    public static Claims parseJWT(String jwtString){
        SecretKey secretKey = generateKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtString)
                .getBody();
    }
}

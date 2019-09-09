package com.xwj.redis;

import com.xwj.redis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("user用户", "张三");
        String user = stringRedisTemplate.opsForValue().get("user用户");
        System.out.println(user);
        Assert.assertEquals("张三", user);
    }

    @Test
    public void testObj(){
        User user=new User(1L,"许文杰123456","aa@126.com");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("xwj", user);
        System.out.println(redisTemplate.opsForValue().get("xwj"));
    }
}

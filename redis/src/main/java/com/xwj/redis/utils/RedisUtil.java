package com.xwj.redis.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private static RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**  默认过期时长，单位：秒 */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    private final static long NOT_EXPIRE = -1;

    @PostConstruct
    public void init(){
        redisUtil=this;
        redisUtil.redisTemplate=this.redisTemplate;
    }

    public static void set(String key, Object value, long expire){
        redisUtil.redisTemplate.opsForValue().set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisUtil.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public static void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public static <T> T  get(String key, Class<T> clazz, long expire) {
        String value = redisUtil.redisTemplate.opsForValue().get(key);
        if(expire != NOT_EXPIRE){
            redisUtil.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public static String get(String key, long expire) {
        String value = redisUtil.redisTemplate.opsForValue().get(key);
        if(expire != NOT_EXPIRE){
            redisUtil.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public static String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public static void delete(String key) {
        redisUtil.redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private static String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private static <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }
}

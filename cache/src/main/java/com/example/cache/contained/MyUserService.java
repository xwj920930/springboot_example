package com.example.cache.contained;

import com.example.cache.model.CacheUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 使用自带缓存service
 **/
@Service
public class MyUserService {
    //@CachePut 该注解会将方法的返回值缓存起来,其中缓存名字是 user,数据的key是userId
    @CachePut(value = "user", key = "#userId")
    public void saveUser(String userId){
        System.err.println("save ..."+userId);
    }
    //@CacheEvict 该注解会删除user缓存中key为userId的数据
    @CacheEvict(value = "user", key = "#userId")
    public void deleteUser(String userId){
        System.err.println("delete ..."+userId);
    }
    //@Cacheable 该注解会在方法执行时，判断缓存user中key为userId
    //的缓存是否存在,如果存在，则直接返回缓存中的数据。如果不存在，则会查数据库，然后将返回结果缓存起来
    @Cacheable(value = "user", key = "#userId")
    public CacheUser getUserById(String userId){
        return getFromDB(userId);
    }

    private CacheUser getFromDB(String userId){
        System.err.println("get from db ..."+userId);
        return new CacheUser(userId);
    }
}

package com.example.cache.custom;

import com.example.cache.model.CacheUser;
import org.springframework.stereotype.Service;

/**
 * 自定义缓存service
 **/
@Service
public class CacheUserService {
    private final CacheManager<CacheUser> cacheManager;
    public CacheUserService() {
        cacheManager=new CacheManager<>();
    }
    public CacheUser getUserById(String userId){
        CacheUser user=cacheManager.getValue(userId);
        if(user != null){
            System.err.println("get from cache ..."+userId);
            return user;
        }
        user=getFromDB(userId);
        cacheManager.addOrUpdateCache(userId,user);
        return  user;
    }
    public void dropUser(String userId){
        cacheManager.evictCache(userId);
    }
    public void reload(){
        cacheManager.evictCache();
    }

    private CacheUser getFromDB(String userId){
        System.err.println("get from db ..."+userId);
        return new CacheUser(userId);
    }
}

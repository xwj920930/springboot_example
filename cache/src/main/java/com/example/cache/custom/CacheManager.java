package com.example.cache.custom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义缓存
 **/
public class CacheManager<T> {
    private final Map<String,T> cache=new ConcurrentHashMap<>();
    public T getValue(String key){
        return cache.get(key);
    }
    public void addOrUpdateCache(String key,T value){
        cache.put(key,value);
    }
    public void evictCache(String key){
        cache.remove(key);
    }
    public void evictCache(){
        cache.clear();
    }
}

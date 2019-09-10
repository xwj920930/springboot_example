package com.xwj.redis.Controller;

import com.xwj.redis.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * value/cacheNames：
 * 缓存的名称
 * 例如：@Cacheable(value=”mycache”) 或者 @Cacheable(value={”cache1”,”cache2”}。
 *
 * key ：缓存的 key，可以为空，
 * 如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合，
 * 例如：@Cacheable(value=”testcache”,key=”#userName”)。
 *
 * condition ：缓存的条件，可以为空
 */
@RestController
public class UserController {

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=new User(1L,"许wj1","aa@126.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }
    @RequestMapping("/getUser/{name}")
    @Cacheable(cacheNames = "user-name",key = "#name")
    public User getUserByName(@PathVariable("name") String name) {
        User user=new User(2L,"许wj2","aa@126.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }
    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
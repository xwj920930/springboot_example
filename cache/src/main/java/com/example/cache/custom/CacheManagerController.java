package com.example.cache.custom;

import com.example.cache.model.CacheUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cache")
public class CacheManagerController {
    @Autowired
    private CacheUserService cacheUserService;

    @GetMapping("/getManagerUser/{userId}")
    public String getManagerUser(@PathVariable(name = "userId") String userId) {
        CacheUser user = cacheUserService.getUserById(userId);
        return user.toString();
    }

    @GetMapping("/dropManagerUser/{userId}")
    public void dropManagerUser(@PathVariable(name = "userId") String userId) {
        cacheUserService.dropUser(userId);
        System.err.println("drop cache:" + userId);
    }

    @GetMapping("/clear")
    public void clear() {
        System.err.println("clear cache");
        cacheUserService.reload();
    }
}
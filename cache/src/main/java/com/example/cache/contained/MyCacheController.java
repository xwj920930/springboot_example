package com.example.cache.contained;

import com.example.cache.model.CacheUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class MyCacheController {
    @Autowired
    private MyUserService myUserService;

    @GetMapping("/saveUser/{userId}")
    public void saveUser(@PathVariable(name = "userId") String userId) {
        myUserService.saveUser(userId);
    }

    @GetMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable(name = "userId") String userId) {
        myUserService.deleteUser(userId);
    }

    @GetMapping("/getUser/{userId}")
    public CacheUser getUser(@PathVariable(name = "userId") String userId) {
        return myUserService.getUserById(userId);
    }
}
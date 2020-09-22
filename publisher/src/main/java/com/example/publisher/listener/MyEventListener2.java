package com.example.publisher.listener;

import com.example.publisher.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
//@Async
public class MyEventListener2 {
    @EventListener(condition = "#user.name != null")
    public void handleEvent(User user){
        System.out.println(user.getName());
        System.out.println(user.getSex());
        System.err.println("this is listener two");
    }
}

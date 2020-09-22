package com.example.publisher.listener;

import com.example.publisher.model.User;
import com.example.publisher.publisher.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
//@Async
public class MyEventListener4 {
    @EventListener
    public void handleEvent(MyEvent<User> publisher){
        System.out.println(publisher.getUser().getName());
        System.out.println(publisher.getUser().getSex());
        System.err.println("this is listener four");
    }
}

package com.example.publisher.register;

import com.example.publisher.model.User;
import com.example.publisher.publisher.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MyEventRegister {
    @Autowired
    private ApplicationEventPublisher publisher; //可使用自带的或者继承ApplicationEvent的类

    public void register(){
        User user = new User();
        user.setName("PC");
        user.setSex("unknown");
        publisher.publishEvent(user);
    }

    public void register2(){
        User user = new User();
        user.setName("PC");
        user.setSex("unknown");
        publisher.publishEvent(new MyEvent<>(user));
    }
}

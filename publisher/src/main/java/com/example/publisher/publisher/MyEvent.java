package com.example.publisher.publisher;

import org.springframework.context.ApplicationEvent;

public class MyEvent<User> extends ApplicationEvent {
    private User user;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(User source) {
        super(source);
        this.user = source;
    }

    public User getUser(){
        return user;
    }
}

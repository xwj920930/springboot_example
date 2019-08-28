package com.xwj.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "xwj")
public class XwjReceiver2 {

    @RabbitHandler
    public void process(String neo) {
        System.err.println("Receiver 2: " + neo);
    }

}

package com.xwj.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.生产者绑定信息到交换器，并且给交换器传递一个待匹配的值
 * 2.交换器绑定了消费者队列，指定匹配规则
 * 3.交换器根据规则匹配生产者传过来的值
 * 4.对于匹配成功的情况，推送数据到绑定的消费者队列
 * 5.消费者从各自的队列取数据
 */
@Configuration
public class TopicRabbitConfig {

    @Bean//绑定消费者队列
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean//绑定消费者队列
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }

    @Bean//交换器
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean//交换器绑定生产者规则与消费者队列
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean//交换器绑定生产者规则与消费者队列
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}

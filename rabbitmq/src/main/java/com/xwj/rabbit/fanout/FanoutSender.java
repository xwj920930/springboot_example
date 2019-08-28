package com.xwj.rabbit.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订阅模式（与多对多区分）
 * 理解：多对多的意思是消费者轮训从一个队列取数据，大家不会重复；订阅是大家取得数据都一样
 * 1.一个生产者，多个消费者
 * 2.每一个消费者都有自己的一个队列
 * 3.生产者没有将消息直接发送到队列，而是发送到了交换机
 * 4.每个队列都要绑定到交换机
 * 5.生产者发送的消息，经过交换机，到达队列，实现一个消息被多个消费者获取的目的
 * 注意：一个消费者队列可以有多个消费者实例，只有其中一个消费者实例会消费
 * 消息发送到没有队列绑定的交换机时，消息将丢失，因为，交换机没有存储消息的能力
 */
@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hi, fanout msg ";
		System.out.println("Sender : " + context);
		//生产者绑定交换器
		this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
	}

}
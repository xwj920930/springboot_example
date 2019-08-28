package com.xwj.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 多个生产者对多个消费者（共用一个队列，与订阅模式区分）
 * 1.同一个消息只能被一个消费者获取
 * 2.默认轮询（round-robin）分发消息
 * 3.可以设置公平分发（能者多劳）
 * // 同一时刻服务器只会发一条消息给消费者
 * channel.basicQos(1);
 * //使用手动确认模式
 * channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
 * // 监听队列改为手动确认
 * channel.basicConsume(QUEUE_NAME, false, consumer);
 * 4.确认模式
 * 自动确认：只管发消息，不关心接受者状态，默认它成功
 * 手动确认：发消息后，服务器等待消费者的反馈，如果没有反馈，该消息将一直处于不可用状态
 */
@Component
public class XwjSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String context = "spirng boot neo queue"+" ****** "+i;
		System.out.println("Sender1 : " + context);
		this.rabbitTemplate.convertAndSend("xwj", context);
	}

}
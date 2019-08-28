package com.xwj.rabbitmq;

import com.xwj.rabbit.many.XwjSender;
import com.xwj.rabbit.many.XwjSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
	@Autowired
	private XwjSender xwjSender;

	@Autowired
	private XwjSender2 xwjSender2;

	@Test
	public void oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			xwjSender.send(i);
		}
	}

	@Test
	public void manyToMany() throws Exception {
		for (int i=0;i<100;i++){
			xwjSender.send(i);
			xwjSender2.send(i);
		}
	}

}
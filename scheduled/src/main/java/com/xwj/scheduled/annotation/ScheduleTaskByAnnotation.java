package com.xwj.scheduled.annotation;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解
 * Scheduled注解：除了支持灵活的参数表达式cron之外，还支持简单的延时操作
 * 例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
 */
@Component
@EnableScheduling
public class ScheduleTaskByAnnotation {
    //@Scheduled(cron = "0/1 * * * * ?")
    @Scheduled(fixedRate = 1000)
    private void task(){
        System.err.println("使用注解的定时方法");
    }
}

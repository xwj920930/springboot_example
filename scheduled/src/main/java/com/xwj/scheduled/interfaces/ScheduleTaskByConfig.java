package com.xwj.scheduled.interfaces;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

/**
 * 基于接口的定时任务
 */
@Configuration
public class ScheduleTaskByConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addFixedRateTask(() -> System.out.println("基于接口的定时任务: " + new Date()), 1000);
//        TriggerTask triggrtTask = new TriggerTask( // 任务内容.拉姆达表达式
//                () -> {System.out.println("执行定时任务2: " + new Date());},
//                // 设置触发器，这里是一个拉姆达表达式，传入的TriggerContext类型，返回的是Date类型
//                triggerContext -> {
//                    // 2.3 返回执行周期(Date)
//                    return new CronTrigger("*/2 * * * * ?").nextExecutionTime(triggerContext);
//                });
//
//        scheduledTaskRegistrar.addTriggerTask(triggrtTask);
    }
}

package com.example.publisher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 开启异步开关
 */
@Configuration
@EnableAsync
public class AsynEventConfiguration implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}

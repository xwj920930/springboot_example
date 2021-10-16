package com.example.xwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 如果项目中没有关于dataSource的配置信息，那么Spring创建dataSource bean时
 * 就会因为缺少相关的信息而报错
 * 因此我们需要在Application类上面增加@EnableAutoConfiguration注解
 *
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DemoApplication {
    public static void main( String[] args ) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

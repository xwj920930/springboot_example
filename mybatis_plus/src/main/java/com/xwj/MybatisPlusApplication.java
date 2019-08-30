package com.xwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@MapperScan("com.xwj.mybatis.mapper")
public class MybatisPlusApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MybatisPlusApplication.class , args);
    }
}

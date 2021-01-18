package com.xwj.rest.pojo;

import lombok.Data;

/**
 * @Description 使用java bean读取配置文件
 * @Author yuki
 * @Date 2021/1/10 20:37
 * @Version 1.0
 **/
@Data
public class Student {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return name+";"+age;
    }
}

package com.example.xwj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 使用java bean读取配置文件
 * @Author yuki
 * @Date 2021/1/10 20:37
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return name+";"+age;
    }
}

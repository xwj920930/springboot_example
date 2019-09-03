package com.xwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebSocketApplication {
    public static void main( String[] args ) {
        SpringApplication.run(WebSocketApplication.class,args);
    }
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}

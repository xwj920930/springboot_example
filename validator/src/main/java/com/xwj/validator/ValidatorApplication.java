package com.xwj.validator;

import com.xwj.validator.model.Demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ValidatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidatorApplication.class,args);
    }
    @GetMapping("/")
    public String hello(Model model){
        model.addAttribute("demo",new Demo());
        return "demo";
    }
}

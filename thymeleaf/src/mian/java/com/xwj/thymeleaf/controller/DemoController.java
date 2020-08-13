package com.xwj.thymeleaf.controller;

import com.xwj.thymeleaf.model.Demo;
import com.xwj.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class DemoController {
    @GetMapping("/demo")
    public ModelAndView demo(){
        ModelAndView modelAndView=new ModelAndView();
        User user = new User(1, "xwj");
        Demo demo=new Demo(user,10000d, LocalDate.now());
        modelAndView.addObject("demo",demo);
        modelAndView.setViewName("demo");
        return modelAndView;
    }
    @GetMapping("/demo1")
    public String demo1(){
        return "demo1";
    }
    @GetMapping("/demo2")
    public String demo2(ModelMap map) {
        User user = new User(1, "xwj");
        Demo demo=new Demo(user,10000d, LocalDate.now());
        map.put("demo", demo);
        map.put("now",new Date());
        return "demo";
    }
}

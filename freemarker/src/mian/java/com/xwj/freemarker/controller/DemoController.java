package com.xwj.freemarker.controller;

import com.xwj.freemarker.model.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class DemoController {
    @GetMapping("/demo")
    public ModelAndView demo(){
        ModelAndView modelAndView=new ModelAndView();
        Demo demo=new Demo(1,"xwj",10000d, LocalDate.now());
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
        Demo demo=new Demo(1,"xwj",10000d, LocalDate.now());
        map.put("demo", demo);
        return "demo";
    }
}

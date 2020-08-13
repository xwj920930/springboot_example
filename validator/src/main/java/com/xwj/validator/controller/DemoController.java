package com.xwj.validator.controller;

import com.xwj.validator.group.ControllerGroup;
import com.xwj.validator.model.Demo;
import com.xwj.validator.model.User;
import com.xwj.validator.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DemoController {
    @Autowired
    DemoService demoService;

    //postman测试用
    @PostMapping("/demo1")
    @ResponseBody
    public String demo(@Valid Demo demo, BindingResult bindingResult){
        StringBuilder stringBuilder=new StringBuilder();
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(e-> stringBuilder.append(e.getDefaultMessage()).append("---"));
        }
        return stringBuilder.toString();
    }

    @GetMapping("/demo")
    public String demo(Model model){
        Demo demo = new Demo();
        demo.setUser(new User());
        model.addAttribute("demo",demo);
        return "demo";
    }
    @GetMapping("/demo1")
    public String demo1(Model model){
        Demo demo = new Demo();
        demo.setUser(new User());
        model.addAttribute("demo",demo);
        return "demo1";
    }
    @GetMapping("/demo2")
    public String demo2(Model model){
        Demo demo = new Demo();
        demo.setUser(new User());
        model.addAttribute("demo",demo);
        return "demo2";
    }
    //页面测试用，使用@valid校验
    @PostMapping("/demoAdd")
    public ModelAndView demoAdd(@Valid Demo demo, BindingResult result){
        ModelAndView model = new ModelAndView();
        model.setViewName("demo");
        model.addObject("demo",demo);
        return model;
    }
    //页面测试用，在controller分组
    @PostMapping("/demoAddControllerGroup")
    public ModelAndView demoAddControllerGroup(@Validated(ControllerGroup.class) Demo demo, BindingResult result){
        ModelAndView model = new ModelAndView();
        model.setViewName("demo1");
        model.addObject("demo",demo);
        return model;
    }
    //页面测试用，在service分组
    @PostMapping("/demoAddServiceGroup")
    public ModelAndView demoAddServiceGroup(Demo demo, BindingResult result){
        ModelAndView model = demoService.operate(demo);
        model.setViewName("demo2");
        return model;
    }
    
    //postman测试用(通用异常返回)
    @PostMapping("/demo2")
    @ResponseBody
    public String demo2(@Valid Demo demo){
        return "ok";
    }
}

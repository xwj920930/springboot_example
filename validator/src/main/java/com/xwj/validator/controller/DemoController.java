package com.xwj.validator.controller;

import com.xwj.validator.model.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DemoController {
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
    //页面测试用
    @PostMapping("/demoAdd")
    public ModelAndView demoAdd(@Valid Demo demo, BindingResult result){
        ModelAndView model = new ModelAndView();
        model.setViewName("demo");
        model.addObject("demo",demo);
        return model;
    }
    //postman测试用(通用异常返回)
    @PostMapping("/demo2")
    @ResponseBody
    public String demo2(@Valid Demo demo){
        return "ok";
    }
}

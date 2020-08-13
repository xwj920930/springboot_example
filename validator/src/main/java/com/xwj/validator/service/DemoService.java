package com.xwj.validator.service;

import com.xwj.validator.group.ServiceGroup;
import com.xwj.validator.model.Demo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Validated
@Service
public class DemoService {
    @Validated(ServiceGroup.ServiceOperate.class)
    public ModelAndView operate(@Valid Demo demo) {
        ModelAndView model = new ModelAndView();
        model.addObject("demo",demo);
        return model;
    }
}

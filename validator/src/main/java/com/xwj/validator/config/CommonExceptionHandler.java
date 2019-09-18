package com.xwj.validator.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @Description 通用异常拦截器
 * @Author yuki
 **/
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity handlerException(BindException e){
        StringBuilder stringBuilder=new StringBuilder();
        List<ObjectError> allErrors = e.getAllErrors();
        allErrors.forEach(err-> stringBuilder.append(err.getDefaultMessage()).append("---"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(stringBuilder);
    }
}

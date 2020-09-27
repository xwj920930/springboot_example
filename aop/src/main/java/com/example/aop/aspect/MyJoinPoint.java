package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyJoinPoint {
    @Pointcut("execution(public * com.example.aop.controller.AopController.*(..))")
    public void pointcut(){}

    @Pointcut("execution(public String com.example.aop.controller.AopController.method2(String)) && args(param)")
    public void paramPointcut(String param){}

    @Before("pointcut()")
    public void before(){
        System.err.println("before");
    }
    
    @After("pointcut()")
    public void after(){
        System.err.println("after");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.err.println("after returning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.err.println("after throwing");
    }

    @Around(value = "paramPointcut(param)", argNames = "joinPoint,param")
    public Object around(ProceedingJoinPoint joinPoint, String param){
        Object proceed = new Object();
        try {
            System.err.println("around before:" + param);
            proceed = joinPoint.proceed();
            System.err.println("around after:" + param);
        } catch (Throwable throwable) {
            System.err.println("around exception");
        }
        return proceed;
    }
}

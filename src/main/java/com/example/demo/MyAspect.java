package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Around("execution(* com.example.demo.HpPrinter.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before !!!");

        Object obj = pjp.proceed();

        System.out.println("around after !!!");

        return obj;
    }

//    @Before("execution(* com.example.demo.HpPrinter.*(..))")
//    public void before() {
//        System.out.println("before !!!");
//    }
//
//    @After("execution(* com.example.demo.HpPrinter.*(..))")
//    public void after() {
//        System.out.println("after !!!");
//    }
}

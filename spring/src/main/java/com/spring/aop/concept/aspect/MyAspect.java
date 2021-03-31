package com.spring.aop.concept.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect
public class MyAspect {

    private static final String pcut = "execution(* *..*.*ServiceImpl.*(..))";

    @Before(value = "MyAspect.fn()")
    public void before() {
        System.out.println("annotation way before advice");
    }

    @AfterReturning(pcut)
    public void after() {
        System.out.println("annotation way after advice");
    }

    @Pointcut("execution(* *..*.*ServiceImpl.*(..))")
    public void fn() {

    }
}

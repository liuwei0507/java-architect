package com.spring.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJ {

    @Pointcut("execution(* com.spring.aop.aspectj.dao.*.*(..))")
    public void pointCutExecution(){

    }

    @Pointcut("execution(* com.spring.aop.aspectj.dao.*.*(..))")
    public void pointCutWithin(){

    }

    /**
     * 通知
     * location
     *
     * login
     */
    @Before("pointCutExecution()")
    public void before() {
        System.out.println("advice");
    }
}

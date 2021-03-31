package com.spring.aop.concept.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 编写通知类（增强类）
 */
public class MyAdvice {

    /**
     * 前置通知
     */
    public void before() {
        System.out.println("before advice...");
    }

    /**
     * 后置通知
     */
    public void afterReturning() {
        System.out.println("after returning advice...");
    }

    /**
     * 最后通知
     */
    public void after() {
        System.out.println("after advice...");
    }

    /**
     * 抛出异常通知
     */
    public void afterThrowing() {
        System.out.println("after throwing advice...");
    }

    /**
     * 环绕通知  场景使用：事务管理
     */
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("around advice---before advice");
        try {
            joinPoint.proceed();
            System.out.println("around advice---after advice");
        } catch (Throwable throwable) {
            System.out.println("around advice---exception");
            throwable.printStackTrace();
        } finally {
            System.out.println("around advice---final advice");
        }
    }
}

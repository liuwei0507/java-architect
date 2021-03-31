package com.spring.aop.concept.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ��д֪ͨ�ࣨ��ǿ�ࣩ
 */
public class MyAdvice {

    /**
     * ǰ��֪ͨ
     */
    public void before() {
        System.out.println("before advice...");
    }

    /**
     * ����֪ͨ
     */
    public void afterReturning() {
        System.out.println("after returning advice...");
    }

    /**
     * ���֪ͨ
     */
    public void after() {
        System.out.println("after advice...");
    }

    /**
     * �׳��쳣֪ͨ
     */
    public void afterThrowing() {
        System.out.println("after throwing advice...");
    }

    /**
     * ����֪ͨ  ����ʹ�ã��������
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

package com.spring.aop.concept.test;

import com.spring.aop.concept.target.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-aop-anno.xml");
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        userService.saveUser();
    }
}

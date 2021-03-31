package com.spring.aop.concept.test;

import com.spring.aop.concept.target.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopXML {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-aop.xml");
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        userService.saveUser();
    }
}

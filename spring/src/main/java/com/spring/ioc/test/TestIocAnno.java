package com.spring.ioc.test;

import com.spring.ioc.annotation.po.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIocAnno {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-ioc-anno.xml");
        Student student = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println(student);
        System.out.println(student2);
    }
}

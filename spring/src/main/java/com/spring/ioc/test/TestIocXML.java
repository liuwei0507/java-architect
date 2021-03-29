package com.spring.ioc.test;

import com.spring.ioc.xml.po.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIocXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-ioc.xml");
        System.out.println("===============================");
        Student student = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println(student.getName());
        System.out.println(student2.getCourse().getName());
    }
}

package com.spring.source.code.test;

import com.spring.factory.support.DefaultListableBeanFactory;
import com.spring.reader.XmlBeanDefinitionReader;
import com.spring.resource.ClassPathResource;
import com.spring.resource.Resource;
import com.spring.source.code.test.po.Student;

public class TestSpringFramework {

    public static void main(String[] args) {
        //1、指定spring配置文件路径
        String location = "beans.xml";
        //2、将路径封装成资源对象
        Resource resource = new ClassPathResource(location);
        //3、创建BeanDefinitionRegistry
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //4、加载spring配置文件，并最终将解析出来的BeanDefinition注册到BeanDefinitionRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        //从容器中获取指定bean名称的对象
        Student student = (Student) beanFactory.getBean("student");
        Student student2 = (Student) beanFactory.getBean("student");
        System.out.println(student);
        System.out.println(student2);
    }
}

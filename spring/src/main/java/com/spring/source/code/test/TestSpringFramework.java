package com.spring.source.code.test;

import com.spring.factory.support.DefaultListableBeanFactory;
import com.spring.reader.XmlBeanDefinitionReader;
import com.spring.resource.ClassPathResource;
import com.spring.resource.Resource;
import com.spring.source.code.test.po.Student;

public class TestSpringFramework {

    public static void main(String[] args) {
        //1��ָ��spring�����ļ�·��
        String location = "beans.xml";
        //2����·����װ����Դ����
        Resource resource = new ClassPathResource(location);
        //3������BeanDefinitionRegistry
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //4������spring�����ļ��������ս�����������BeanDefinitionע�ᵽBeanDefinitionRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        //�������л�ȡָ��bean���ƵĶ���
        Student student = (Student) beanFactory.getBean("student");
        Student student2 = (Student) beanFactory.getBean("student");
        System.out.println(student);
        System.out.println(student2);
    }
}

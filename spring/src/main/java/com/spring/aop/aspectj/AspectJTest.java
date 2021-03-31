package com.spring.aop.aspectj;

import com.spring.aop.aspectj.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectJTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IndexDao indexDao = context.getBean(IndexDao.class);
        indexDao.query();
    }
}

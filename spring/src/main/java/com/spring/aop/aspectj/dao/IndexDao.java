package com.spring.aop.aspectj.dao;

import org.springframework.stereotype.Repository;

@Repository
public class IndexDao {
    public void query() {
        System.out.println("query");
    }
}

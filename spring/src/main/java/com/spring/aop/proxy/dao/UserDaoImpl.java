package com.spring.aop.proxy.dao;

public class UserDaoImpl implements UserDao {
    public void query() {
        System.out.println("====query====");
    }
}

package com.spring.aop.proxy.test;

import com.spring.aop.proxy.dao.UserDao;
import com.spring.aop.proxy.dao.UserDaoImpl;
import com.spring.aop.proxy.extend.UserDaoLogImpl;
import com.spring.aop.proxy.organized.UserLogDao;
import com.spring.aop.proxy.util.ProxyUtil;

public class TestProxy {
    public static void main(String[] args) {
//        UserDaoImpl userDao = new UserDaoImpl();
//        UserDaoLogImpl userDao = new UserDaoLogImpl();
        UserDao userDao = new UserDaoImpl();
        UserDao proxy = (UserDao) ProxyUtil.getInstance();

    }
}

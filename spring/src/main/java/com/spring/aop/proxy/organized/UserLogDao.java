package com.spring.aop.proxy.organized;

import com.spring.aop.proxy.dao.UserDao;

public class UserLogDao implements UserDao {
    private UserDao userDao;

    public UserLogDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void query() {
        System.out.println("========log======");
        userDao.query();
    }
}

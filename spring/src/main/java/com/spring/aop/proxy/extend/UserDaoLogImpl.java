package com.spring.aop.proxy.extend;

import com.spring.aop.proxy.dao.UserDaoImpl;

public class UserDaoLogImpl extends UserDaoImpl {
    @Override
    public void query() {
        System.out.println("=======log======");
        super.query();
    }
}

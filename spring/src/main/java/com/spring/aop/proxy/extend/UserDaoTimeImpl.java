package com.spring.aop.proxy.extend;

import com.spring.aop.proxy.dao.UserDaoImpl;

public class UserDaoTimeImpl extends UserDaoImpl {
    @Override
    public void query() {
        System.out.println("=======time======");
        super.query();
    }
}

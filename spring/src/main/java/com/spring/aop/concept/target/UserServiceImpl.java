package com.spring.aop.concept.target;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    public void saveUser() {
        System.out.println("save user");
        // �׳��쳣�Ĵ���
        // System.out.println(1 / 0);
    }

    public void saveUser(String name) {
        System.out.println("save user : name");
    }

    public void updateUser() {
        System.out.println("update user");
    }
}

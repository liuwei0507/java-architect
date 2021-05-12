package com.tool.mat;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        int i=1;
        while (true){
            User user = new User();
            user.setAge(i);
            user.setName("name is "+i);
            i++;
            list.add(user);
        }

    }
}

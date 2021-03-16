package com.mybatis.phase02.mapper;

import com.mybatis.phase02.po.User;

public interface UserMapper {
    public User findUserById(int id);

    public int insertUser(User user);
}

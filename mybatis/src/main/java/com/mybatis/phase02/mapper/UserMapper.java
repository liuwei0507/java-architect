package com.mybatis.phase02.mapper;

import com.mybatis.phase02.po.User;
import com.mybatis.phase02.vo.QueryVo;

import java.util.List;

public interface UserMapper {
    public User findUserById(int id);

    public int insertUser(User user);

    List<User> findUserList(QueryVo queryVo);
}

package com.mybatis.phase01.dao;


import com.mybatis.phase01.po.User;

public interface UserDao {

	User findUserById(int id);

}

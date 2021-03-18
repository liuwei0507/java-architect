package com.mybatis.source.dao;

import com.mybatis.framework.sqlsession.SqlSession;
import com.mybatis.framework.sqlsession.SqlSessionFactory;
import com.mybatis.source.po.User;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    // 注入sqlSessionFactory
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(User user) {
        // sqlsessionFactory工厂类去创建sqlsession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // sqlsession接口，开发人员使用它对数据库进行增删改查操作
        User selectUser = sqlSession.selectOne("test.findUserById", user);
        return selectUser;

    }
}

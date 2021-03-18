package com.mybatis.framework.sqlsession;

import java.util.List;

/**
 * 表示一个sql会话，就是一次CRUD操作
 */
public interface SqlSession {
    public <T> T selectOne(String statementId, Object param);

    public <T> List<T> selectList(String statementId, Object params);
}

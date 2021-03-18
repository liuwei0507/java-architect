package com.mybatis.framework.sqlsession;

import java.util.List;

/**
 * ��ʾһ��sql�Ự������һ��CRUD����
 */
public interface SqlSession {
    public <T> T selectOne(String statementId, Object param);

    public <T> List<T> selectList(String statementId, Object params);
}

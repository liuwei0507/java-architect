package com.mybatis.framework.sqlsession;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;
import com.mybatis.framework.executor.CachingExecutor;
import com.mybatis.framework.executor.Executor;
import com.mybatis.framework.executor.SimpleExecutor;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if (list == null || list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("ֻ�ܷ���һ������");
        }
    }

    @Override
    public <T> List<T> selectList(String statementId, Object params) {
        // ����statementId��ȡMappedStatement����
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        // ִ��Statement�Ĳ�����ִ�з�ʽ�ж��֣�һ���Ǵ��ж��������ִ�з�ʽ��һ���ǻ���ִ�з�ʽ[ֻ����һ�����棬����ִ�з�ʽ�ֳַɼ��֣�����ִ������������ִ������]��
        // �˴����Կ��Ƿŵ�MappedStatement�����У��ö����п��Ը����Ƿ������˶���������ȷ�����������ĸ�Executor
        Executor executor = new CachingExecutor(new SimpleExecutor());
        return executor.query(mappedStatement, configuration, params);
    }
}

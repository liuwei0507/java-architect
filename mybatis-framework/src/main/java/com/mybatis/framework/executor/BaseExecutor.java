package com.mybatis.framework.executor;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����ִ��������Ҫ����һ������
 */
public abstract class BaseExecutor implements Executor {
    private Map<String, List<Object>> oneLevelCache = new HashMap<>();

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        // ��ȡ����ֵ��sql���
        String sql = mappedStatement.getSqlSource().getBoundSql(param).getSql();
        //��һ�������и���sql����ȡ��ѯ���
        List<Object> result = oneLevelCache.get(sql);
        if (result != null) {
            return (List<T>) result;
        }
        //���û�н�����������Ӧ�Ĵ�����ȥ����
        result = queryFromDatabase(mappedStatement, configuration, param);
        oneLevelCache.put(sql, result);
        return (List<T>) result;
    }

    abstract List<Object> queryFromDatabase(MappedStatement mappedStatement, Configuration configuration, Object param);

}

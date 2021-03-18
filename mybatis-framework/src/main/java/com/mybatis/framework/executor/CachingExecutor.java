package com.mybatis.framework.executor;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;

import java.util.List;

/**
 * �����������
 */
public class CachingExecutor implements Executor {

    // ����ִ����
    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object params) {
        // �Ӷ��������и���sql����ȡ������������������ô�棿����������
        // ����У���ֱ�ӷ��أ����û�������ί�и�����ִ����ȥ����
        return delegate.query(mappedStatement, configuration, params);
    }
}

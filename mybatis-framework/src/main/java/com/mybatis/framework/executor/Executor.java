package com.mybatis.framework.executor;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;

import java.util.List;

public interface Executor {
    /**
     * @param mappedStatement ��ȡsql������γ��ε���Ϣ
     * @param configuration   ��ȡ����Դ����
     * @param param           ��ζ���
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);
}

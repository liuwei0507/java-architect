package com.mybatis.framework.sqlsource;

import com.mybatis.framework.sqlnode.MixedSqlNode;
import com.mybatis.framework.sqlnode.SqlNode;

public class RawSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public RawSqlSource(MixedSqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    /**
     * ��sqlsessionִ�е�ʱ�򣬲ŵ��ø÷���
     */
    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}

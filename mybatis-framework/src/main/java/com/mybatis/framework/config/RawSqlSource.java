package com.mybatis.framework.config;

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

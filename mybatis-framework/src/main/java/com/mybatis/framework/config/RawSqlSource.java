package com.mybatis.framework.config;

public class RawSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public RawSqlSource(MixedSqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    /**
     * 在sqlsession执行的时候，才调用该方法
     */
    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}

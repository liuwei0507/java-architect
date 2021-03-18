package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;

public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        // sqlÎÄ±¾×·¼Ó
        context.appendSql(sqlText);
    }
}

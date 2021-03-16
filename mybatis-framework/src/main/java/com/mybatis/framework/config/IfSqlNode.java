package com.mybatis.framework.config;

import com.mybatis.framework.utils.OgnlUtils;

public class IfSqlNode implements SqlNode {

    /**
     * 布尔表达式
     */
    private String test;

    private SqlNode rootSqlNode;

    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean evaluateBoolean = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
        if (evaluateBoolean) {
            rootSqlNode.apply(context);
        }
    }
}

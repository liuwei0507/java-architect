package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;
import com.mybatis.framework.utils.OgnlUtils;

public class IfSqlNode implements SqlNode {

    /**
     * �������ʽ
     */
    private String test;

    private SqlNode rootSqlNode;

    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        // ʹ��Ognl��api����test��ǩ�����еĲ������ʽ���д�����ȡ����ֵ
        boolean evaluateBoolean = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
        if (evaluateBoolean) {
            // ���test��ǩ�����еı��ʽ�ж�Ϊtrue���Ž����ӽڵ�Ĵ���
            rootSqlNode.apply(context);
        }
    }
}

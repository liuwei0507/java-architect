package com.mybatis.framework.sqlsource;

import com.mybatis.framework.sqlnode.MixedSqlNode;
import com.mybatis.framework.sqlnode.SqlNode;

/**
 * ר�ŷ�װ�ʹ������${}�Ͷ�̬sql��ǩ��sql���
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public DynamicSqlSource(MixedSqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }


    /**
     * ��sqlsessionִ�е�ʱ�򣬲ŵ��ø÷���
     */
    @Override
    public BoundSql getBoundSql(Object param) {
        // �����ȵ���SqlNode�Ĵ�������̬��ǩ��${}����һ��
        DynamicContext context = new DynamicContext(param);
        rootSqlNode.apply(context);
        // �ٵ���SqlSourceParser������#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql());
        return sqlSource.getBoundSql(param);
    }
}

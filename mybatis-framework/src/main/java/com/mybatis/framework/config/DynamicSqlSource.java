package com.mybatis.framework.config;

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
        return null;
    }
}

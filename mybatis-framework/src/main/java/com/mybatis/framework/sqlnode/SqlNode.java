package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;

/**
 * �ṩ��sql�ű��Ľ���
 */
public interface SqlNode {

    void apply(DynamicContext context);
}

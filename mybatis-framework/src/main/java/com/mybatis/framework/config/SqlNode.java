package com.mybatis.framework.config;

/**
 * �ṩ��sql�ű��Ľ���
 */
public interface SqlNode {

    void apply(DynamicContext context);
}

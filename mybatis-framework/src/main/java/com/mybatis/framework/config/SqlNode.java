package com.mybatis.framework.config;

/**
 * 提供对sql脚本的解析
 */
public interface SqlNode {

    void apply(DynamicContext context);
}

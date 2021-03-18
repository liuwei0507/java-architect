package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;

/**
 * 提供对sql脚本的解析
 */
public interface SqlNode {

    void apply(DynamicContext context);
}

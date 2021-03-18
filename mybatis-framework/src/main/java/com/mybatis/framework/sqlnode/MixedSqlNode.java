package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;

import java.util.ArrayList;
import java.util.List;

public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes = new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode: sqlNodes) {
            sqlNode.apply(context);
        }
    }
}

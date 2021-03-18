package com.mybatis.framework.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ã���װ�Ѿ���ȫ�����ɹ���sql���ͽ��������Ĳ�����Ϣ����
 */
public class BoundSql {
    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMappings(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }
}

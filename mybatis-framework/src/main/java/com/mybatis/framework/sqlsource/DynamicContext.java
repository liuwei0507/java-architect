package com.mybatis.framework.sqlsource;

import java.util.HashMap;
import java.util.Map;

/**
 *  * ���ã��洢SqlNode���������в�����sqlƬ�Σ�������ַ���ƴ�� �洢SqlNode������������Ҫ�������Ϣ
 */
public class DynamicContext {
    private StringBuilder sb = new StringBuilder();

    private Map<String, Object> bindings = new HashMap<String, Object>();

    public DynamicContext(Object param) {
        bindings.put("_parameter", param);
    }

    public void appendSql(String sql) {
        sb.append(sql);
        sb.append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

}

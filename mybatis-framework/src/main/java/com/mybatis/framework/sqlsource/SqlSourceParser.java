package com.mybatis.framework.sqlsource;

import com.mybatis.framework.utils.GenericTokenParser;
import com.mybatis.framework.utils.ParameterMappingTokenHandler;

/**
 *  * ��DynamicSqlSource��RawSqlSource������StaticSqlSource
 *  * StaticSqlSource�洢�ľ���ֻ��?��sql����Լ���Ӧ��sql��Ϣ
 */
public class SqlSourceParser {

    public SqlSource parse(String sqlText) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        // tokenParser.parse(sqlText)������δ����ģ�����ֵ���Ѵ���ģ�û��${}��#{}��
        String sql = tokenParser.parse(sqlText);
        return new StaticSqlSource(sql, tokenHandler.getParameterMappings());
    }
}

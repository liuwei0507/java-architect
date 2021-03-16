package com.mybatis.framework.config;

import org.dom4j.Element;

public class XMLStatementParser {
    private Configuration configuration;

    public XMLStatementParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectElement, String namespace) {
        // һ��MappedStatement���󣬾Ͷ�Ӧһ��select��ǩ
        String id = selectElement.attributeValue("id");
        String parameterType = selectElement.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveClass(parameterType);

        String resultType = selectElement.attributeValue("resultType");
        Class<?> resultTypeClass = resolveClass(resultType);

        String statementType = selectElement.attributeValue("statementType");
        statementType = statementType == null || statementType.equals("") ? "prepare" : statementType;
        // SqlSource���Ƿ�װ��SQL���
        // ��ʱ��װ��SQL�����û�н��д���ģ�ʲôʱ�����أ�
        // ����ʱ����������SqlSessionִ�е�ʱ��
        // SELECT * FROM user WHERE id = #{id}
        // String sqlText = selectElement.getTextTrim();
        // SqlSource sqlSource = new SqlSource(sqlText);
        SqlSource sqlSource = createSqlSource(selectElement);

        String statementId = namespace + "." + id;
        // �˴�ʹ�ù��췽������set����ȥ��ֵ�Ļ����о�����ˬ
        // ������������У�����û��
        // ʹ�ù�����ģʽ����
        MappedStatement mappedStatement = new MappedStatement(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
        configuration.setMappedStatement(statementId, mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptParser scriptParser = new XMLScriptParser(configuration);
        SqlSource sqlSource = scriptParser.parseScriptNode(selectElement);
        return sqlSource;
    }

    private Class<?> resolveClass(String parameterType) {
        try {
            return Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

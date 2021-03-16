package com.mybatis.framework.sqlsession;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.XMLConfigParser;
import com.mybatis.framework.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 *  ʹ�ù�����ģʽ��SqlSessionFactory���д���
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        // ��ȡConfiguration����
        Document document = DocumentUtils.readDocument(inputStream);
        XMLConfigParser configParser = new XMLConfigParser();
        Configuration configuration = configParser.parse(document.getRootElement());
        return build(configuration);
    }

    public SqlSessionFactory build(Reader reader) {
        return null;
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }

}

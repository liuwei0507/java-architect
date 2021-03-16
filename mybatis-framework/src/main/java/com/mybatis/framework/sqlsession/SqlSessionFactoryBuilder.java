package com.mybatis.framework.sqlsession;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.XMLConfigParser;
import com.mybatis.framework.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 *  使用构建者模式对SqlSessionFactory进行创建
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        // 获取Configuration对象
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

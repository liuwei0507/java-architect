package com.mybatis.framework.config;

import org.dom4j.Element;

import java.util.List;

public class XMLMapperParser {
    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @param rootElement <mapper namespace="test"/>
     */
    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        //此处可以用Xpath语法来进行通配
        List<Element> elements = rootElement.elements("select");
        for (Element selectElement : elements) {
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(selectElement, namespace);
        }

    }
}

package com.mybatis.framework.config;

import com.mybatis.framework.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser() {
        configuration = new Configuration();
    }

    /**
     * @param rootElement <configuration></configuration>
     * @return
     */
    public Configuration parse(Element rootElement) {
        parseEnvironments(rootElement.element("environments"));
        parseMappers(rootElement.element("mappers"));
        return configuration;
    }

    private void parseMappers(Element element) {
        List<Element> elements = element.elements("mapper");
        for (Element mapperElement : elements) {
            parseMapper(mapperElement);
        }
    }

    private void parseMapper(Element mapperElement) {
        //��ȡ�����ļ���·��
        String resource = mapperElement.attributeValue("resource");
        //��ȡָ��ӳ���ļ���IO��
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //��ȡӳ���ļ���Ӧ��Document����
        Document document = DocumentUtils.readDocument(inputStream);
        //����mapper��ǩ����ȥ����document
        XMLMapperParser mapperParser = new XMLMapperParser(configuration);
        mapperParser.parse(document.getRootElement());
    }

    private void parseEnvironments(Element element) {
        String defaultEnvId = element.attributeValue("default");
        if (defaultEnvId == null || "".equals(defaultEnvId)) {
            return;
        }
        List<Element> elements = element.elements("environment");
        for (Element envElement : elements) {
            String envId = envElement.attributeValue("id");
            // �ж�defaultEnvId��envId�Ƿ�һ�£�һ���ټ�������
            if (defaultEnvId.equals(envId)) {
                parseEnvironment(envElement);
            }
        }
    }

    private void parseEnvironment(Element envElement) {
        Element dataSourceEnv = envElement.element("dataSource");
        String type = dataSourceEnv.attributeValue("type");
        type = type == null || type.equals("") ? "DBCP" : type;
        if (type.equals("DBCP")) {
            Properties properties = parseProperties(dataSourceEnv);
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            // ������������DataSource���󣬷�װ��Configuration������
            configuration.setDataSource(dataSource);
        }
    }

    private Properties parseProperties(Element dataSourceEnv) {
        Properties properties = new Properties();
        List<Element> elements = dataSourceEnv.elements("property");
        for (Element element : elements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name, value);
        }
        return properties;
    }
}

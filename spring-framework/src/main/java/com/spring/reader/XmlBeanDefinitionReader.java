package com.spring.reader;

import com.spring.factory.BeanDefinitionRegistry;
import com.spring.resource.Resource;
import com.spring.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

public class XmlBeanDefinitionReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = resource.getResourceAsStream();
        Document document = DocumentReader.createDocument(inputStream);
        XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.loadBeanDefinitions(document.getRootElement());
    }
}

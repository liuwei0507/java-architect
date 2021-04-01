package com.spring.reader;

import com.spring.definition.BeanDefinition;
import com.spring.definition.PropertyValue;
import com.spring.definition.RuntimeBeanReference;
import com.spring.definition.TypeStringValue;
import com.spring.factory.BeanDefinitionRegistry;
import com.spring.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;

public class XmlBeanDefinitionDocumentReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * ����spring ��ǩ������н���
     *
     * @param rootElement
     */
    public void loadBeanDefinitions(Element rootElement) {
        // ��ȡ<bean>���Զ����ǩ������mvc:interceptors��
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // ��ȡ��ǩ����
            String name = element.getName();
            if (name.equals("bean")) {
                parseDefaultElement(element);
            } else {
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
        // TODO
    }

    private void parseDefaultElement(Element beanElement) {
        try {
            if (beanElement == null) {
                return;
            }
            //��ȡid����
            String id = beanElement.attributeValue("id");
            // ��ȡname����
            String name = beanElement.attributeValue("name");
            // ��ȡclass����
            String clazzName = beanElement.attributeValue("class");
            if (clazzName == null || "".equals(clazzName)) {
                return;
            }
            Class<?> clazzType = Class.forName(clazzName);
            // ��ȡinit-method����
            String initMethod = beanElement.attributeValue("init-method");
            // ��ȡscope ����
            String scope = beanElement.attributeValue("scope");
            String beanName = id == null ? name : id;
            beanName = beanName == null ? clazzType.getSimpleName() : beanName;
            //����BeanDefinition����
            BeanDefinition beanDefinition = new BeanDefinition(clazzName, beanName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);
            // ��ȡproperty�ӱ�ǩ����
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }

            // ע��BeanDefinition��Ϣ
            this.beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if (propertyElement == null) {
            return;
        }
        //��ȡname����
        String name = propertyElement.attributeValue("name");
        //��ȡvalue����
        String value = propertyElement.attributeValue("value");
        //��ȡref����
        String ref = propertyElement.attributeValue("ref");
        // ���value��ref����ֵ���򷵻�
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }
        /**
         * PropertyValue�ͷ�װ��һ��property��ǩ����Ϣ
         */
        PropertyValue pv = null;
        if (value != null && !value.equals("")) {
            // ��Ϊspring�����ļ��е�value��String���ͣ��������е�����ֵ�Ǹ��ָ����ģ�������Ҫ�洢����
            TypeStringValue typeStringValue = new TypeStringValue(value);
            Class<?> targetType = ReflectUtils.getTypeByFieldName(beanDefinition.getClazzName(), name);
            typeStringValue.setTargetType(targetType);
            pv = new PropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(pv);
        } else if (ref != null && !ref.equals("")) {
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            pv = new PropertyValue(name, reference);
            beanDefinition.addPropertyValue(pv);
        }
    }

}

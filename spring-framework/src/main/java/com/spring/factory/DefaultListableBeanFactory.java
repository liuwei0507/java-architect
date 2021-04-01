package com.spring.factory;

import com.spring.definition.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory implements BeanDefinitionRegistry{
    private Map<String, BeanDefinition> beanDefinitions = new HashMap();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }
}

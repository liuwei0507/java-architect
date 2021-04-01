package com.spring.factory.support;

import com.spring.definition.BeanDefinition;
import com.spring.factory.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitions = new HashMap();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }

    @Override
    public <T> List<T> getBeansByType(Class<?> type) {
        return null;
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        return null;
    }
}

package com.spring.factory;

import com.spring.definition.BeanDefinition;

public interface BeanDefinitionRegistry {
    /**
     * 建立beanName和BeanDefinition的映射关系
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);
}

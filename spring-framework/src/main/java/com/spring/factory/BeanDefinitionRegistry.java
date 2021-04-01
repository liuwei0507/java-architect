package com.spring.factory;

import com.spring.definition.BeanDefinition;

public interface BeanDefinitionRegistry {
    /**
     * ����beanName��BeanDefinition��ӳ���ϵ
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);
}

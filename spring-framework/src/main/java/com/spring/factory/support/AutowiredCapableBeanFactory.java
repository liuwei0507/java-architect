package com.spring.factory.support;

import com.spring.definition.BeanDefinition;
import com.spring.factory.BeanFactory;

/**
 * ���д���Bean�Ĺ��ܣ�����Bean����Ҫ��Bean����װ��
 */
public interface AutowiredCapableBeanFactory extends BeanFactory {

    /**
     * ����Beanʵ��
     * @param name
     * @param beanDefinition
     * @return
     */
    Object createBean(String name, BeanDefinition beanDefinition);
}

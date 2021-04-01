package com.spring.factory.support;

import com.spring.definition.BeanDefinition;
import com.spring.factory.BeanFactory;

/**
 * 具有创建Bean的功能，创建Bean就需要对Bean进行装配
 */
public interface AutowiredCapableBeanFactory extends BeanFactory {

    /**
     * 创建Bean实例
     * @param name
     * @param beanDefinition
     * @return
     */
    Object createBean(String name, BeanDefinition beanDefinition);
}

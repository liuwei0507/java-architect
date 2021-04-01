package com.spring.factory.support;

/**
 * 负责对单例bean的注册和管理工作
 */
public interface SingletonBeanRegistry {

    /**
     * 根据bean的name获取单例bean
     */
    Object getSingleton(String name);

    /**
     * 将单例bean添加到缓存中
     * @param name
     * @param bean
     */
    void addSingleton(String name, Object bean);
}

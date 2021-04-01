package com.spring.factory.support;

import com.spring.factory.BeanFactory;

import java.util.List;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * 根据bean的类型，获取它以及子类型在容器中对应的bean实例
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> getBeansByType(Class<?> type);

    /**
     * 根据bean的类型，获取它以及子类型在容器中对应的bean的名称
     * @param type
     * @return
     */
    List<String> getBeanNamesByType(Class<?> type);
}

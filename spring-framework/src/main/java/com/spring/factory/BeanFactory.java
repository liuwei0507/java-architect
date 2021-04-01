package com.spring.factory;

/**
 * 基础容器的顶级接口，也是spring容器的最重要的接口
 */
public interface BeanFactory {

    /**
     * 根据bean的name获取指定的bean实例
     * @param name
     * @return
     */
    Object getBean(String name);
}

package com.spring.factory.support;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean的缓存管理
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletons = new HashMap();
    @Override
    public Object getSingleton(String name) {
        return this.singletons.get(name);
    }

    @Override
    public void addSingleton(String name, Object bean) {
        this.singletons.put(name, bean);
    }
}

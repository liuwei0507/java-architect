package com.spring.factory.support;

import com.spring.definition.BeanDefinition;

/**
 *  * 符合抽象模板设计模式的一个抽象类 抽象模板方法设计模式，由两个角色组成（父类和子类） 父类指定统一的处理步骤 子类负责特定步骤的细节实现
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ListableBeanFactory {
    @Override
    public Object getBean(String name) {
        // 1.获取缓存中的单例bean的实例
        Object singleton = getSingleton(name);
        // 2.如果有结果，则直接返回
        if (singleton != null) {
            return singleton;
        }
        // 3.如何没有结果，则获取指定名称的BeanDefinition对象
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        // 4.通过BeanDefinition中的scope信息，判断该Bean是单例还是多例？，如果是单例，则需要加入单例bean的缓存
        if (beanDefinition.isSingleton()) {
            // 4.1 根据BeanDefinition创建bean实例
            singleton = createBean(name, beanDefinition);
            // 4.2 将创建出来的单例Bean放入单例bean的缓存（Map集合）
            addSingleton(name, singleton);
        } else if (beanDefinition.isPrototype()) {
            // 5.如果是多例Bean
            // 5.1 根据BeanDefinition创建bean实例,并返回
            return createBean(name, beanDefinition);
        }
        return singleton;
    }

    public abstract Object createBean(String name, BeanDefinition beanDefinition);


    /**
     * 定义抽象方法，由子类来实现
     *
     * @param name
     * @return
     */
    public abstract BeanDefinition getBeanDefinition(String name);

}

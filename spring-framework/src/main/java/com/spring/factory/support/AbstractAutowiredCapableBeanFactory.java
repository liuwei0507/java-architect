package com.spring.factory.support;

import com.spring.definition.BeanDefinition;
import com.spring.definition.PropertyValue;
import com.spring.definition.RuntimeBeanReference;
import com.spring.definition.TypeStringValue;
import com.spring.utils.ReflectUtils;

import java.util.List;

/**
 * 负责创建bean的实例和装配它的属性
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory
        implements AutowiredCapableBeanFactory {


    @Override
    public Object createBean(String name, BeanDefinition beanDefinition) {
        // 1. 获取BeanDefinition中的bean的类型（Class)
        Class<?> type = getResolvedClass(beanDefinition);
        //2. 创建bean的实例（使用构造器去创建bean的实例，也是new对象）
        Object bean = createInstance(type);
        // 3. 填充bean的属性（DI）
        populateBean(bean, beanDefinition);
        //4. 调用 Bean的初始化代码（实现了InitializingBean接口的类或者是bean标签中指定了init-metho属性的方法）
        initialBean(bean, beanDefinition);
        return bean;
    }

    /**
     * bean的初始化
     * @param bean
     * @param beanDefinition
     */
    private void initialBean(Object bean, BeanDefinition beanDefinition) {
        // 1. aware接口的处理
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }// ....
        }
        // TODO 2. 对实现了InitializingBean接口的类，调用它的afterPropertiesSet方法
        // 3. 如果bean标签中的init-method属性有值，则调用指定值对应的方法（AOP处理）
        initMethod(bean, beanDefinition);
    }

    private void initMethod(Object bean, BeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if (initMethod == null) {
            return;
        }
        // TODO BeanPostProcessor执行前置处理方法
        ReflectUtils.invokeMethod(bean, initMethod);
        // TODO BeanPostProcessor执行后置处理方法（AOP逻辑的开始）
    }

    /**
     * 填充Bean的属性
     * @param bean
     * @param beanDefinition
     */
    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue: propertyValues) {
            // 获取属性信息中的name， 因为最终给bean实例进行属性填充的话，需要按照name进行填充
            String name = propertyValue.getName();
            // 获取属性信息中的value，该value需要特殊处理（TypedStringValue和RuntimeBeanReference）
            Object value = propertyValue.getValue();

            Object valueToUse = null;
            if (value instanceof TypeStringValue) {
                TypeStringValue typeStringValue = (TypeStringValue) value;
                String stringValue = typeStringValue.getValue();
                //需要进行属性添加到类中，指定name的类型
                Class<?> targetType = typeStringValue.getTargetType();
                // TODO 此处可以使用策略模式进行优化
                if (targetType == Integer.class) {
                    valueToUse = Integer.parseInt(stringValue);
                } else if (targetType == String.class) {
                    valueToUse = stringValue;
                }
            } else if (value instanceof RuntimeBeanReference) {
                RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
                String ref = runtimeBeanReference.getRef();
                // TODO 容易引起循环依赖问题
                valueToUse = getBean(ref);
            }

            // 利用反射，去设置bean实例的属性（根据name去设置对应的属性值）
            ReflectUtils.setProperty(bean, name, valueToUse);
        }
    }

    /**
     * bean的实例创建由多种：通过构造器，通过实例工厂，通过工厂方法
     * @param beanClass
     * @return
     */
    private Object createInstance(Class<?> beanClass) {
        // TODO 如果有实例工厂，则通过实例工厂创建bean的实例
        // TODO 如果有工厂方法，则通过工厂方法创建bean的实例
        // 通过构造器去创建bean的实例
        Object bean = ReflectUtils.createObject(beanClass);
        return bean;
    }

    /**
     * 获取BeanDefinition中的bean的类型（Class)
     * @param beanDefinition
     * @return
     */
    private Class<?> getResolvedClass(BeanDefinition beanDefinition) {
        Class<?> type = null;
        try {
            String beanClazzName = beanDefinition.getClazzName();
            type = Class.forName(beanClazzName);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return type;
    }
}

package com.spring.factory.support;

import com.spring.definition.BeanDefinition;
import com.spring.definition.PropertyValue;
import com.spring.definition.RuntimeBeanReference;
import com.spring.definition.TypeStringValue;
import com.spring.utils.ReflectUtils;

import java.util.List;

/**
 * ���𴴽�bean��ʵ����װ����������
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory
        implements AutowiredCapableBeanFactory {


    @Override
    public Object createBean(String name, BeanDefinition beanDefinition) {
        // 1. ��ȡBeanDefinition�е�bean�����ͣ�Class)
        Class<?> type = getResolvedClass(beanDefinition);
        //2. ����bean��ʵ����ʹ�ù�����ȥ����bean��ʵ����Ҳ��new����
        Object bean = createInstance(type);
        // 3. ���bean�����ԣ�DI��
        populateBean(bean, beanDefinition);
        //4. ���� Bean�ĳ�ʼ�����루ʵ����InitializingBean�ӿڵ��������bean��ǩ��ָ����init-metho���Եķ�����
        initialBean(bean, beanDefinition);
        return bean;
    }

    /**
     * bean�ĳ�ʼ��
     * @param bean
     * @param beanDefinition
     */
    private void initialBean(Object bean, BeanDefinition beanDefinition) {
        // 1. aware�ӿڵĴ���
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }// ....
        }
        // TODO 2. ��ʵ����InitializingBean�ӿڵ��࣬��������afterPropertiesSet����
        // 3. ���bean��ǩ�е�init-method������ֵ�������ָ��ֵ��Ӧ�ķ�����AOP����
        initMethod(bean, beanDefinition);
    }

    private void initMethod(Object bean, BeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if (initMethod == null) {
            return;
        }
        // TODO BeanPostProcessorִ��ǰ�ô�����
        ReflectUtils.invokeMethod(bean, initMethod);
        // TODO BeanPostProcessorִ�к��ô�������AOP�߼��Ŀ�ʼ��
    }

    /**
     * ���Bean������
     * @param bean
     * @param beanDefinition
     */
    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue: propertyValues) {
            // ��ȡ������Ϣ�е�name�� ��Ϊ���ո�beanʵ�������������Ļ�����Ҫ����name�������
            String name = propertyValue.getName();
            // ��ȡ������Ϣ�е�value����value��Ҫ���⴦��TypedStringValue��RuntimeBeanReference��
            Object value = propertyValue.getValue();

            Object valueToUse = null;
            if (value instanceof TypeStringValue) {
                TypeStringValue typeStringValue = (TypeStringValue) value;
                String stringValue = typeStringValue.getValue();
                //��Ҫ����������ӵ����У�ָ��name������
                Class<?> targetType = typeStringValue.getTargetType();
                // TODO �˴�����ʹ�ò���ģʽ�����Ż�
                if (targetType == Integer.class) {
                    valueToUse = Integer.parseInt(stringValue);
                } else if (targetType == String.class) {
                    valueToUse = stringValue;
                }
            } else if (value instanceof RuntimeBeanReference) {
                RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
                String ref = runtimeBeanReference.getRef();
                // TODO ��������ѭ����������
                valueToUse = getBean(ref);
            }

            // ���÷��䣬ȥ����beanʵ�������ԣ�����nameȥ���ö�Ӧ������ֵ��
            ReflectUtils.setProperty(bean, name, valueToUse);
        }
    }

    /**
     * bean��ʵ�������ɶ��֣�ͨ����������ͨ��ʵ��������ͨ����������
     * @param beanClass
     * @return
     */
    private Object createInstance(Class<?> beanClass) {
        // TODO �����ʵ����������ͨ��ʵ����������bean��ʵ��
        // TODO ����й�����������ͨ��������������bean��ʵ��
        // ͨ��������ȥ����bean��ʵ��
        Object bean = ReflectUtils.createObject(beanClass);
        return bean;
    }

    /**
     * ��ȡBeanDefinition�е�bean�����ͣ�Class)
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

package com.spring.factory.support;

import com.spring.definition.BeanDefinition;

/**
 *  * ���ϳ���ģ�����ģʽ��һ�������� ����ģ�巽�����ģʽ����������ɫ��ɣ���������ࣩ ����ָ��ͳһ�Ĵ����� ���ฺ���ض������ϸ��ʵ��
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ListableBeanFactory {
    @Override
    public Object getBean(String name) {
        // 1.��ȡ�����еĵ���bean��ʵ��
        Object singleton = getSingleton(name);
        // 2.����н������ֱ�ӷ���
        if (singleton != null) {
            return singleton;
        }
        // 3.���û�н�������ȡָ�����Ƶ�BeanDefinition����
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        // 4.ͨ��BeanDefinition�е�scope��Ϣ���жϸ�Bean�ǵ������Ƕ�����������ǵ���������Ҫ���뵥��bean�Ļ���
        if (beanDefinition.isSingleton()) {
            // 4.1 ����BeanDefinition����beanʵ��
            singleton = createBean(name, beanDefinition);
            // 4.2 �����������ĵ���Bean���뵥��bean�Ļ��棨Map���ϣ�
            addSingleton(name, singleton);
        } else if (beanDefinition.isPrototype()) {
            // 5.����Ƕ���Bean
            // 5.1 ����BeanDefinition����beanʵ��,������
            return createBean(name, beanDefinition);
        }
        return singleton;
    }

    public abstract Object createBean(String name, BeanDefinition beanDefinition);


    /**
     * ������󷽷�����������ʵ��
     *
     * @param name
     * @return
     */
    public abstract BeanDefinition getBeanDefinition(String name);

}

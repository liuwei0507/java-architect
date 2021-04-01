package com.spring.factory.support;

/**
 * ����Ե���bean��ע��͹�����
 */
public interface SingletonBeanRegistry {

    /**
     * ����bean��name��ȡ����bean
     */
    Object getSingleton(String name);

    /**
     * ������bean��ӵ�������
     * @param name
     * @param bean
     */
    void addSingleton(String name, Object bean);
}

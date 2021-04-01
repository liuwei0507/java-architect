package com.spring.factory.support;

import com.spring.factory.BeanFactory;

import java.util.List;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * ����bean�����ͣ���ȡ���Լ��������������ж�Ӧ��beanʵ��
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> getBeansByType(Class<?> type);

    /**
     * ����bean�����ͣ���ȡ���Լ��������������ж�Ӧ��bean������
     * @param type
     * @return
     */
    List<String> getBeanNamesByType(Class<?> type);
}

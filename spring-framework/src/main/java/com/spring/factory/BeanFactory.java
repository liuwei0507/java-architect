package com.spring.factory;

/**
 * ���������Ķ����ӿڣ�Ҳ��spring����������Ҫ�Ľӿ�
 */
public interface BeanFactory {

    /**
     * ����bean��name��ȡָ����beanʵ��
     * @param name
     * @return
     */
    Object getBean(String name);
}

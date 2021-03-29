package com.design_pattern.singleton;

/*
 * ����ʵ��֮����ʽʵ��
 *
 *
 * ˼�룺��Ҫ�����ʱ����ȥ��������
 *
 * ����ʽ���ģʽ����һ�ֽз����ӳټ���
 *
 *
 * ����ʽ����ģʽ���裺
 * 		1������˽��
 * 		2������˽�о�̬��Ա�������Ȳ���ʼ��
 * 		3�����幫����̬��������ȡ�������
 * 			�ж���ͷ������ж���
 * 			û�ж�����ȥ����
 *
 * �̰߳�ȫ���⣬�ж����ݣ�
 * 		1���Ƿ���ڶ��߳�	��
 * 		2���Ƿ��й�������	��
 * 		3���Ƿ���ڷ�ԭ���Բ���
 *
 *
 *
 * ���������������Ҫ�󣬵��ǣ�����ʽ����ʵ�֣������̰߳�ȫ��������ν����
 * �������Ȼ��뵽��getSingletonInstance������synchronized�ؼ��֣�SingletonFactory3
 */
public class LazySingleton1 {

    private LazySingleton1() {
    }

    private static LazySingleton1 lazySingleton1 = null;

    public static LazySingleton1 getLazySingleton1() {
        if (lazySingleton1 == null) {
            lazySingleton1 = new LazySingleton1();
        }
        return lazySingleton1;
    }
}

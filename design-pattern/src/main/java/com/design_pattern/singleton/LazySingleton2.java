package com.design_pattern.singleton;

/*
 * ��getSingletonInstance������synchronized�ؼ���
 *
 *
 * synchronized�ؼ�����ס������������������÷����������ϻ������½�
 * ��Ϊÿ�ε���getInstance()����Ҫ�Զ�������
 * ��ʵ�ϣ�ֻ���ڵ�һ�δ��������ʱ����Ҫ������֮��Ͳ���Ҫ��
 * ���ԣ�����ط���Ҫ�Ľ�
*/
public class LazySingleton2 {

    private LazySingleton2() {
    }

    private static LazySingleton2 lazySingleton2 = null;

    public static synchronized LazySingleton2 getLazySingleton2() {
        if (lazySingleton2 == null) {
            lazySingleton2 = new LazySingleton2();
        }
        return lazySingleton2;
    }
}

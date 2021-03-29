package com.design_pattern.singleton;

/**
 * ˫�ؼ�����
 */
public class LazySingleton5 {

    private LazySingleton5() {
    }

    private volatile static LazySingleton5 lazySingleton5 = null;


    public static LazySingleton5 getLazySingleton5() {
        // B�̼߳�⵽student��Ϊ��
        if (lazySingleton5 == null) {
            synchronized (LazySingleton5.class) {
                lazySingleton5 = new LazySingleton5();
                // A�̱߳�ָ�������ˣ��պ��ȸ�ֵ�ˣ�����ûִ���깹�캯����
            }
        }
        return lazySingleton5;// ����B�߳�ִ��ʱ��������������δ��ʼ������
    }
}

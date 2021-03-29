package com.design_pattern.singleton;

/**
 *  * ����ģʽ
 *  *
 *  * ͬʱ���ڴ��У�ֻ��һ��������ڡ�
 *  *
 *  * ��α�֤һ�������ڴ���ֻ����һ��ʵ���أ�
 *  1������˽��
 *  2��ʹ��˽�о�̬��Ա������ʼ���������
 *  3�������ṩ��̬����������ȡ�������
 *  *
 *  *
 *  * ����ģʽ������ʵ�ַ�ʽ�� 1������ʽ(�ӳټ���) 2������ʽ
 */
public class HungrySingleton {

    private HungrySingleton(){}

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }
}

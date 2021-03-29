package com.design_pattern.singleton;

/*
 * �ƺ������֮ǰ�ᵽ�����⣬��synchronized�ؼ��ּ������ڲ���Ҳ����˵�����õ�ʱ���ǲ���Ҫ�����ģ�ֻ����instanceΪnull�������������ʱ�����Ҫ������������һ����������
 * ���ǣ�����������������п���������ģ�������������
 * 	��Javaָ���д�������͸�ֵ�����Ƿֿ����еģ�Ҳ����˵instance = new Singleton();����Ƿ�����ִ�еġ�
 *  ����JVM������֤�������������Ⱥ�˳��Ҳ����˵�п���JVM��Ϊ�µ�Singletonʵ������ռ䣬Ȼ��ֱ�Ӹ�ֵ��instance��Ա��Ȼ����ȥ��ʼ�����Singletonʵ����
 *  �����Ϳ��ܳ����ˣ�������A��B�����߳�Ϊ����
 *
 * 		a>A��B�߳�ͬʱ�����˵�һ��if�ж�
 *
 * 		b>A���Ƚ���synchronized�飬����instanceΪnull��������ִ��instance = new Singleton();
 *
 * 		c>����JVM�ڲ����Ż�����(ָ��������)��JVM�Ȼ�����һЩ�����Singletonʵ���Ŀհ��ڴ棬����ֵ��instance��Ա��ע���ʱJVMû�п�ʼ��ʼ�����ʵ������Ȼ��A�뿪��synchronized�顣
 *
 * 		d>B����synchronized�飬����instance��ʱ����null������������뿪��synchronized�鲢��������ظ����ø÷����ĳ���
 *
 * 		e>��ʱB�̴߳���ʹ��Singletonʵ����ȴ������û�б���ʼ�������Ǵ������ˡ�
 *
 * 	���Գ������п��ܷ���������ʵ���������й����Ǻܸ��ӵģ���������ǾͿ��Կ�������������д���̻߳����µĳ�������Ѷȣ�����ս�ԡ�
 *  ���ǶԸó�������һ���Ż���
 */
public class LazySingleton3 {

    private LazySingleton3() {
    }

    private static LazySingleton3 lazySingleton3 = null;

    public static synchronized LazySingleton3 getLazySingleton3() {
        if (lazySingleton3 == null) {
            // �������ַ�ʽ�����ڶ����ѡ���������
            // JVM�Ż����ƣ��ȷ����ڴ�ռ䣬�ٳ�ʼ��
            synchronized (LazySingleton3.class) {
                lazySingleton3 = new LazySingleton3();
                //new ---- ����JVM�жѿռ�---�������ڴ��ַ���浽ջ�ڴ��lazySingleton3������---��������
            }
        }
        return lazySingleton3;
    }
}

package com.design_pattern.singleton;

/**
 * 双重检查加锁
 */
public class LazySingleton5 {

    private LazySingleton5() {
    }

    private volatile static LazySingleton5 lazySingleton5 = null;


    public static LazySingleton5 getLazySingleton5() {
        // B线程检测到student不为空
        if (lazySingleton5 == null) {
            synchronized (LazySingleton5.class) {
                lazySingleton5 = new LazySingleton5();
                // A线程被指令重排了，刚好先赋值了；但还没执行完构造函数。
            }
        }
        return lazySingleton5;// 后面B线程执行时将引发：对象尚未初始化错误。
    }
}

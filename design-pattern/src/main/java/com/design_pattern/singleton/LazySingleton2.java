package com.design_pattern.singleton;

/*
 * 对getSingletonInstance方法加synchronized关键字
 *
 *
 * synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降
 * 因为每次调用getInstance()，都要对对象上锁
 * 事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了
 * 所以，这个地方需要改进
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

package com.design_pattern.singleton;

/**
 *  * 单例模式
 *  *
 *  * 同时在内存中，只有一个对象存在。
 *  *
 *  * 如何保证一个类在内存中只能有一个实例呢？
 *  1：构造私有
 *  2：使用私有静态成员变量初始化本身对象
 *  3：对外提供静态公共方法获取本身对象
 *  *
 *  *
 *  * 单例模式有两种实现方式： 1：懒汉式(延迟加载) 2：饿汉式
 */
public class HungrySingleton {

    private HungrySingleton(){}

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }
}

package com.design_pattern.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        //����ʽ
        System.out.println(HungrySingleton.getHungrySingleton());
        System.out.println(HungrySingleton.getHungrySingleton());

        //����ʽ
        System.out.println(LazySingleton1.getLazySingleton1());
        System.out.println(LazySingleton1.getLazySingleton1());

        System.out.println(LazySingleton2.getLazySingleton2());
        System.out.println(LazySingleton2.getLazySingleton2());

        System.out.println(LazySingleton3.getLazySingleton3());
        System.out.println(LazySingleton3.getLazySingleton3());

        System.out.println(LazySingleton4.getLazySingleton4());
        System.out.println(LazySingleton4.getLazySingleton4());

        System.out.println(LazySingleton5.getLazySingleton5());
        System.out.println(LazySingleton5.getLazySingleton5());
    }
}

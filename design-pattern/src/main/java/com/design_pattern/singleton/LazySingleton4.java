package com.design_pattern.singleton;

public class LazySingleton4 {

    private LazySingleton4() {
    }

    private static class SingletonFactory {
        private static LazySingleton4 lazySingleton4 = new LazySingleton4();
    }


    public static LazySingleton4 getLazySingleton4() {
        return SingletonFactory.lazySingleton4;
    }
}

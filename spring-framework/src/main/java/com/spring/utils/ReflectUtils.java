package com.spring.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

    public static Class<?> getTypeByFieldName(String beanClassName, String name) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Object createObject(Class<?> beanClass, Object... args) {
        try {
            Constructor<?> constructor = beanClass.getConstructor();
            // 默认调用无参构造进行对象的创建
            return constructor.newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void setProperty(Object beanInstance, String name, Object valueToUse) {
        try {
            Class<?> clazz = beanInstance.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(beanInstance, valueToUse);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void invokeMethod(Object beanInstance, String initMethod) {
        try {
            Class<?> clazz = beanInstance.getClass();
            Method method = clazz.getDeclaredMethod(initMethod);
            // 设置允许访问私有方法和变量，此处也称之为暴力破解
            method.setAccessible(true);
            method.invoke(beanInstance);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

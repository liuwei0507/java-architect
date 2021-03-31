package com.spring.aop.annotation.util;

import com.spring.aop.annotation.anno.Entity;

public class CommonUtil {

    public static String buildQuerySqlForEntity(Object object) {
        Class<?> clazz = object.getClass();

        if (clazz.isAnnotationPresent(Entity.class)) {
            Entity entity = clazz.getAnnotation(Entity.class);
            String value = entity.value();
            System.out.println(value);
        }
        return "";
    }
}

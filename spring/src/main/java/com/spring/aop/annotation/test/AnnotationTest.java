package com.spring.aop.annotation.test;

import com.spring.aop.annotation.entity.CityEntity;
import com.spring.aop.annotation.util.CommonUtil;

public class AnnotationTest {
    public static void main(String[] args) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId("1");
        cityEntity.setAddress("wuhan");
        CommonUtil.buildQuerySqlForEntity(cityEntity);
    }
}

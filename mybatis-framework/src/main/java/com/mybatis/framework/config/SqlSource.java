package com.mybatis.framework.config;

public interface SqlSource {

    BoundSql getBoundSql(Object param);

}

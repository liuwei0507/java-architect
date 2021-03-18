package com.mybatis.framework.executor;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;

import java.util.List;

public interface Executor {
    /**
     * @param mappedStatement 获取sql语句和入参出参等信息
     * @param configuration   获取数据源对象
     * @param param           入参对象
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);
}

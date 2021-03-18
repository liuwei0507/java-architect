package com.mybatis.framework.executor;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;

import java.util.List;

/**
 * 处理二级缓存
 */
public class CachingExecutor implements Executor {

    // 基本执行器
    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object params) {
        // 从二级缓存中根据sql语句获取处理结果（二级缓存怎么存？？？？？）
        // 如果有，则直接返回，如果没有则继续委托给基本执行器去吃力
        return delegate.query(mappedStatement, configuration, params);
    }
}

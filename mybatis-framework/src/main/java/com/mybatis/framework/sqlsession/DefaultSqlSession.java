package com.mybatis.framework.sqlsession;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;
import com.mybatis.framework.executor.CachingExecutor;
import com.mybatis.framework.executor.Executor;
import com.mybatis.framework.executor.SimpleExecutor;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if (list == null || list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("只能返回一个对象");
        }
    }

    @Override
    public <T> List<T> selectList(String statementId, Object params) {
        // 根据statementId获取MappedStatement对象
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        // 执行Statement的操作（执行方式有多种：一种是带有二级缓存的执行方式、一种是基本执行方式[只带有一级缓存，基本执行方式又分成几种：基本执行器、批处理执行器等]）
        // 此处可以考虑放到MappedStatement对象中，该对象中可以根据是否配置了二级缓存来确定创建的是哪个Executor
        Executor executor = new CachingExecutor(new SimpleExecutor());
        return executor.query(mappedStatement, configuration, params);
    }
}

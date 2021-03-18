package com.mybatis.framework.executor;

import com.mybatis.framework.sqlsource.BoundSql;
import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.MappedStatement;
import com.mybatis.framework.sqlsource.ParameterMapping;
import com.mybatis.framework.sqlsource.SqlSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 普通执行JDBC程序
 */
public class SimpleExecutor extends BaseExecutor {

    @Override
    List<Object> queryFromDatabase(MappedStatement mappedStatement, Configuration configuration, Object param) {
        List<Object> results = new ArrayList<>();

        try {
            // 获取连接
            Connection connection = getConnection(configuration);
            // 获取SQL语句
            BoundSql boundSql = getBoundSql(mappedStatement.getSqlSource(), param);
            String statementType = mappedStatement.getStatementType();

            // 使用mybatis的四大组件来优化
            if ("prepared".equals(statementType)) {
                //创建statement
                PreparedStatement statement = createStatement(connection, boundSql.getSql());
                //设置参数
                handleParameter(statement, boundSql, param);
                //执行statement
                ResultSet resultSet = statement.executeQuery();
                // 处理结果
                handleResult(resultSet, mappedStatement, results);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return results;
    }

    private void handleResult(ResultSet resultSet, MappedStatement mappedStatement, List<Object> results) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 从结果集中一行一行的取数据
        // 每一行数据，再一列一列的去数据（包括列的名称和列的值）
        // 最终将获取到的每一列的值映射到目标对象的指定属性中（列的名称和属性的名称要一致）
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        while (resultSet.next()) {
            // 要映射的结果目标对象
            Object result = resultTypeClass.newInstance();
            // 获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, resultSet.getObject(columnName));
            }
            results.add(result);
        }
    }

    private void handleParameter(PreparedStatement statement, BoundSql boundSql, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        //判断参数类型，如果是简单冷，直接处理
        if (param instanceof Integer) {
            statement.setObject(1, Integer.parseInt(param.toString()));
        } else if (param instanceof String) {
            statement.setObject(1, param.toString());
        } else {
            // 获取参数集合信息（#{} 处理之后得到参数信息
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                Object valueToUser = null;
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // #{} 中的参数名称，也应该和POJO类型中的属性名一直
                String name = parameterMapping.getName();
                // 使用反射获取指定name的值
                Class<?> clazz = param.getClass();
                // 获取指定名称的属性对象
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                valueToUser = field.get(param);
                statement.setObject(i + 1, valueToUser);
            }
        }
    }

    private PreparedStatement createStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement;
    }

    private BoundSql getBoundSql(SqlSource sqlSource, Object param) {
        BoundSql boundSql = sqlSource.getBoundSql(param);
        return boundSql;
    }

    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        Connection connection = dataSource.getConnection();
        return connection;
    }
}

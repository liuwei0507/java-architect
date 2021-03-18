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
 * ��ִͨ��JDBC����
 */
public class SimpleExecutor extends BaseExecutor {

    @Override
    List<Object> queryFromDatabase(MappedStatement mappedStatement, Configuration configuration, Object param) {
        List<Object> results = new ArrayList<>();

        try {
            // ��ȡ����
            Connection connection = getConnection(configuration);
            // ��ȡSQL���
            BoundSql boundSql = getBoundSql(mappedStatement.getSqlSource(), param);
            String statementType = mappedStatement.getStatementType();

            // ʹ��mybatis���Ĵ�������Ż�
            if ("prepared".equals(statementType)) {
                //����statement
                PreparedStatement statement = createStatement(connection, boundSql.getSql());
                //���ò���
                handleParameter(statement, boundSql, param);
                //ִ��statement
                ResultSet resultSet = statement.executeQuery();
                // ������
                handleResult(resultSet, mappedStatement, results);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return results;
    }

    private void handleResult(ResultSet resultSet, MappedStatement mappedStatement, List<Object> results) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // �ӽ������һ��һ�е�ȡ����
        // ÿһ�����ݣ���һ��һ�е�ȥ���ݣ������е����ƺ��е�ֵ��
        // ���ս���ȡ����ÿһ�е�ֵӳ�䵽Ŀ������ָ�������У��е����ƺ����Ե�����Ҫһ�£�
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        while (resultSet.next()) {
            // Ҫӳ��Ľ��Ŀ�����
            Object result = resultTypeClass.newInstance();
            // ��ȡ�������Ԫ����
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
        //�жϲ������ͣ�����Ǽ��䣬ֱ�Ӵ���
        if (param instanceof Integer) {
            statement.setObject(1, Integer.parseInt(param.toString()));
        } else if (param instanceof String) {
            statement.setObject(1, param.toString());
        } else {
            // ��ȡ����������Ϣ��#{} ����֮��õ�������Ϣ
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                Object valueToUser = null;
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // #{} �еĲ������ƣ�ҲӦ�ú�POJO�����е�������һֱ
                String name = parameterMapping.getName();
                // ʹ�÷����ȡָ��name��ֵ
                Class<?> clazz = param.getClass();
                // ��ȡָ�����Ƶ����Զ���
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

package com.mybatis.source;

import com.mybatis.framework.config.Configuration;
import com.mybatis.framework.config.Resources;
import com.mybatis.framework.config.XMLConfigParser;
import com.mybatis.framework.sqlsession.SqlSessionFactory;
import com.mybatis.framework.sqlsession.SqlSessionFactoryBuilder;
import com.mybatis.framework.utils.DocumentUtils;
import com.mybatis.source.dao.UserDao;
import com.mybatis.source.dao.UserDaoImpl;
import com.mybatis.source.po.User;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

public class MybatisFrameWorkTest {
    @Test
    public void testInitConfiguration() throws Exception {
        //
        String resource = "SqlMapConfig.xml";
        //
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //
        Document document = DocumentUtils.readDocument(inputStream);
        //
        XMLConfigParser configParser = new XMLConfigParser();

        // <configuration>
        Configuration configuration = configParser.parse(document.getRootElement());
        System.out.println(configuration);
    }

    /**
     * ????mybatis??????
     */
    @Test
    public void testQueryUserById() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // SqlSessionFactory???????????????????????SqlSessionFactory?????
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User param = new User();
        param.setId(1);
        User user = userDao.findUserById(param);
        System.out.println(user);
    }
}

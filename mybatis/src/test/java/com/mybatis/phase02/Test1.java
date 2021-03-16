package com.mybatis.phase02;

import com.mybatis.phase02.po.User;
import com.mybatis.phase02.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

/**
 * 测试入门案例
 *
 * @author think
 *
 */
public class Test1 {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws Exception{
		// 加载全局配置文件（同时把映射文件也加载了）
		String resource = "com/mybatis/phase02/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		com.mybatis.phase02.po.User user = mapper.findUserById(12);
		System.out.println(user);
		sqlSession.close();
	}

    @Test
    public void insertUser() {
	    User user = new User();
	    user.setUsername("insertUser");
	    user.setBirthday(new Date());
	    user.setSex("female");
	    user.setAddress("polly");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int id = mapper.insertUser(user);
		sqlSession.commit();
		System.out.println(id);
	}
}

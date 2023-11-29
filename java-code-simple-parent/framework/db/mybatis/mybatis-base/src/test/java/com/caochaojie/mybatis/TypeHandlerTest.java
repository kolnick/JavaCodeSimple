package com.caochaojie.mybatis;

import com.caochaojie.mybatis.bean.User;
import com.caochaojie.mybatis.mapper.UserMapper;
import com.caochaojie.mybatis.util.DBUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @Author: caochaojie
 * @Date: 2023-11-29 15:32
 */
public class TypeHandlerTest {
    private UserMapper userMapper;
    SqlSession sqlSession;

    @BeforeTest
    public void before() {
        sqlSession = DBUtils.openSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @AfterTest
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test() {
        User user = new User();
        user.setPassword("222222");
        user.setUsername("李四");
        Date regTime = new Date();
        user.setRegTime(regTime);
        userMapper.insertUser(user);
    }

}

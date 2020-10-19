package com.mybatis_stu.spring_mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperTest {
    public static void main(String[] args) throws IOException {
        String xmlPath = "com/mybatis_stu/spring_mybatis/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(xmlPath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


    }
}

package com.mybatis_stu.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperBaseTest {
    private SqlSession tmpSqlSession ;
    private SqlSession getSqlSession(boolean autoCommit) throws IOException {
        //读取配置文件
        //全局配置文件的路径
        String resource = "com/mybatis_stu/mybatis_config/a_mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(autoCommit);
        return sqlSession ;
    }
    protected <T> T getMapper(Class<T> type)   {
        //开启自动提交事务
        try {
            tmpSqlSession= getSqlSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpSqlSession.getMapper(type);
    }
    protected void closeSqlSession(){
        closeSqlSession(tmpSqlSession);
    }
    public void closeSqlSession(SqlSession sqlSession){
        if(sqlSession != null){
            sqlSession.close();
        }
    }
}

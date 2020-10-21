package com.mybatis_stu.f_pageHelper.myInterceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * 自定义mybatis的拦截器
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class MyPageHelpInceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(MyPageHelpInceptor.class);
    private String appCode = "mavenweb" ;
    private static int QUERY_MAX_LIMITED = 1000 ;
    private String sqlID_prefix ;
    private String framework_sqlID_prefix ;

    public MyPageHelpInceptor(){
        sqlID_prefix = "com.mybatis_stu.mapper" ;
        framework_sqlID_prefix = "com.mybatis_stu.mapper" ;
        System.out.println("framework_sqlID_prefix = "+ framework_sqlID_prefix);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("comming into MyPageHelpInceptor.intercept()");
        Object[] args = invocation.getArgs();
        System.out.println(Arrays.toString(args));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {

//        return Plugin.wrap(target,this);
        return target ;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

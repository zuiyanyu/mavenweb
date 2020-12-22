package com.spring_stu.spring_tx.g_多数据源引用;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Transactional("forum") //绑定到forum的事务管理器中
public @interface ForumTransactional {
}

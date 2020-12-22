package com.spring_stu.dao;

import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface JNDIBaseDAO {

    public List<Map<String, Object>> select(String sql);

    public void update(String how);

    public void insert(Object obj);

    public void insert(String sql);

    public void save(String sql);

    public void edit(String sql);

    public void execute(String sql, PreparedStatementCallback callback);

    public void delete(String sql);

    public void insertObjects(String[] sqls);

    public Connection getConnection() throws Exception;

}


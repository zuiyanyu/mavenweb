package com.mavenweb.service;

import java.util.List;
import java.util.Map;

public interface DynamicTabService {
    /**
     * 动态向表中插入数据
     * @param fromLineNum 从给出的数据第几行开始
     * @param tabName  表英文名
     * @param fieldNameList 表字段列表
     * @param dataList 表数据
     * @param <T>
     * @return
     * @throws RuntimeException
     */
    <T> int dynamicAddData(int fromLineNum, String tabName, List<String> fieldNameList, List<T> dataList) throws RuntimeException;

    /**
     * 运行查询sql
     * @param pageNum
     * @param pageSize
     * @param sql
     * @throws Exception
     */
    public Map<String, Object> runSelectSql(int pageNum, int pageSize, String sql) throws RuntimeException;
}

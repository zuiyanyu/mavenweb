package com.mavenweb.service.impl;

import com.mavenweb.domain.DynamicTab;
import com.mavenweb.mapper.DynamicTabMapper;
import com.mavenweb.service.DynamicTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Service
public class DynamicTabServiceImpl implements DynamicTabService {
    @Resource
    private DynamicTabMapper dynamicTabMapper;
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    public DynamicTabServiceImpl(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource) ;
    }

    @Override
    public <T> int  dynamicAddData(int fromLineNum, String tabName, List<String> fieldNameList, List<T> dataList) throws RuntimeException {
        //插入数据
        DynamicTab dynamicTab = new DynamicTab(tabName, fieldNameList, dataList);
        int insertedCount = dynamicTabMapper.dymicAddData(dynamicTab);
        if(insertedCount != dataList.size()){
            throw new RuntimeException("从第[{"+fromLineNum+"}]行开始插入的数据失败！本批次要插入的数据量为："+dataList.size()+"，实际插入的数据量为："+insertedCount);
        }
        return insertedCount;
    }

    @Override
    public Map<String, Object>  runSelectSql(int pageNum, int pageSize, String selectSql) throws RuntimeException {
        if (pageNum <0 || pageSize <=0){
            throw new RuntimeException("pageNum必须大于等于0！并且pageSize必须大于0！");
        }
        List<String> excludeSql = Arrays.asList("insert", "update", "delete", "drop", "alter", "truncate");
        for (String keyword : excludeSql) {
            if(selectSql.toLowerCase().contains(keyword)){
                throw new RuntimeException("只允许执行select语句，不允许执行"+keyword+"语句");
            }
        }
        //sql预处理
        selectSql = selectSql.replaceAll(";","");

       /* if(sql.toLowerCase().){}*/
        //第一步：获取总记录数
        String selectCountSql ="select count(1) count from ("+selectSql+")t" ;
        Long selectCount = jdbcTemplate.queryForObject(selectCountSql, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int index) throws SQLException {
                long aLong = resultSet.getLong(1);
                return aLong;
            }
        });

        //第二步：进行分页查询的sql拼接
        //如果入参sqli结尾没有limit语句，就进行分页查询，否则就不进行分页查询。
        int limitIndex = selectSql.toLowerCase().lastIndexOf("limit");
        if(limitIndex==-1 || !selectSql.substring(limitIndex).toLowerCase().contains(")")){
            long totalPageNum = 0 ;
            if(selectCount%pageSize==0) {
                totalPageNum = selectCount/pageSize;
            }else{
                totalPageNum = selectCount/pageSize +1;
            }
            long fromPage =pageNum <= totalPageNum? pageNum : totalPageNum ;
            long fromIndex = fromPage * pageSize ;
            selectSql = new StringBuilder()
                     .append(selectSql).append("\n")
                     .append("limit").append(" ").append(fromIndex).append(",").append(pageSize)
                     .toString();
        }
        //第三步：开始进行selectSql的查询和映射
        Map<String, Object> dataInfoMap = jdbcTemplate.queryForObject(selectSql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                //获取字段别名列表

                List<String> columnLabelList = new ArrayList<>();
                for (int index = 1; index <= columnCount; index++) {
                    //表名:字段别名
                    columnLabelList.add(metaData.getTableName(index)+":"+metaData.getColumnLabel(index)) ;
                }
                //获取数据列
                List<Map<String,Object>> dataMapList = new ArrayList<>();
                Map<String ,Object> dataMap ;
                while(resultSet.next()){
                    dataMap = new HashMap<>();
                    for (int index = 1; index <= columnCount; index++) {
                        //< (表名:字段别名),字段值 >
                        dataMap.put(columnLabelList.get(index-1),resultSet.getObject(index));
                    }
                    dataMapList.add(dataMap);
                }

                Map<String,Object> retMap = new HashMap<>();
                retMap.put("total",selectCount);
                retMap.put("columnLabelList",columnLabelList);
                retMap.put("dataMapList",dataMapList);
                return retMap;
            }
        });
        return dataInfoMap;
    }
}

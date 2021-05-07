package com.hadoop.bean;

import lombok.Data;

/**
 * 事件日志Bean之用户收藏
 */
@Data
public class AppFavorites {
    private int id;//主键
    private int course_id;//商品id
    private int userid;//用户ID
    private String add_time;//创建时间
}

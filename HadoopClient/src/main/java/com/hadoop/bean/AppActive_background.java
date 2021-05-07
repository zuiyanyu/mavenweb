package com.hadoop.bean;

import lombok.Data;

/**
 * 事件日志Bean之用户后台活跃
 */
@Data
public class AppActive_background {
    private String active_source;//1=upgrade(升级),2=download(下载),3=plugin_upgrade(更新插件)
}

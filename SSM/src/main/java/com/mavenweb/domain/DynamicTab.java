package com.mavenweb.domain;

import java.util.List;

public class DynamicTab<T> {
    private String tabName ;
    private List<String> fields ;
    private List<T> datasList ;

    public DynamicTab() {
    }
    public DynamicTab(String tabName, List<String> fields, List<T> datasList) {
        this.tabName = tabName;
        this.fields = fields;
        this.datasList = datasList;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<T> getDatasList() {
        return datasList;
    }

    public void setDatasList(List<T> datasList) {
        this.datasList = datasList;
    }

    @Override
    public String toString() {
        return "DynamicTab{" +
                "tabName='" + tabName + '\'' +
                ", fields=" + fields +
                ", datasList=" + datasList +
                '}';
    }
}

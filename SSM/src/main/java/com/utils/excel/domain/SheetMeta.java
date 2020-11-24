package com.utils.excel.domain;

import java.util.Date;
import java.util.List;

public class SheetMeta {
    //sheet的主键
    private String sheetId ;
    //sheet所在的工作簿(工作簿主键)
    private String workBookId ;
    //sheet在workBook中的索引号 从1开始
    private int sheetIndex ;
    //sheet页的名称
    private String sheetName ;
    //sheet中的数据行总数
    private Integer sheetRowCount;

    //excel解析用到的header中文名称
    private String headerJson ;
    //excel解析用到的header英文名称
    private String colJson ;
    //header所在的行 从1开始
    private Integer headerInRowIndex ;
    //data数据所在的行 从 1开始
    private Integer dataInRowIndex ;


    //sheet中的每行的数据信息
    private List<RowMeta> RowMetaList ;
    //header中文名称
    private List<String> headerList ;
    //header英文名称
    private List<String> colList ;
    //创建日期
    private Date creatTime ;

    @Override
    public String toString() {
        return "SheetInfo{" +
                "sheetId='" + sheetId + '\'' +
                ", workBookId='" + workBookId + '\'' +
                ", sheetIndex=" + sheetIndex +
                ", sheetName='" + sheetName + '\'' +
                ", sheetRowCount=" + sheetRowCount +
                ", headerJson='" + headerJson + '\'' +
                ", colJson='" + colJson + '\'' +
                ", headerInRowIndex=" + headerInRowIndex +
                ", dataInRowIndex=" + dataInRowIndex +
                ", RowMetaList=" + RowMetaList +
                ", headerList=" + headerList +
                ", colList=" + colList +
                ", creatTime=" + creatTime +
                '}';
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(String workBookId) {
        this.workBookId = workBookId;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getSheetRowCount() {
        return sheetRowCount;
    }

    public void setSheetRowCount(Integer sheetRowCount) {
        this.sheetRowCount = sheetRowCount;
    }

    public String getHeaderJson() {
        return headerJson;
    }

    public void setHeaderJson(String headerJson) {
        this.headerJson = headerJson;
    }

    public String getColJson() {
        return colJson;
    }

    public void setColJson(String colJson) {
        this.colJson = colJson;
    }

    public Integer getHeaderInRowIndex() {
        return headerInRowIndex;
    }

    public void setHeaderInRowIndex(Integer headerInRowIndex) {
        this.headerInRowIndex = headerInRowIndex;
    }

    public Integer getDataInRowIndex() {
        return dataInRowIndex;
    }

    public void setDataInRowIndex(Integer dataInRowIndex) {
        this.dataInRowIndex = dataInRowIndex;
    }

    public List<RowMeta> getRowMetaList() {
        return RowMetaList;
    }

    public void setRowMetaList(List<RowMeta> rowMetaList) {
        RowMetaList = rowMetaList;
    }

    public List<String> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    public List<String> getColList() {
        return colList;
    }

    public void setColList(List<String> colList) {
        this.colList = colList;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}

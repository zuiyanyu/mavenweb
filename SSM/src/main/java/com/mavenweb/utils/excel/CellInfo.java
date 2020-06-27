package com.mavenweb.utils.excel;

import java.util.Date;

//存储每个excell单元格所需要的信息
public class CellInfo<V> {
    //单元格的主键
    private String cellId ;
    //单元格所在的行（行主键）
    private String rowId ;

    //当前单元格所在的header名
    private String headerName ;

    //当前单元格所在的行索引 从1开始
    private Integer rowIndex ;

    //当前单元格所在的列- 数值表示 从1开始
    private Integer columnIndex ;

    //当前单元格所在的列- 字符表示
    private String columnName ;

    //当前单元格存储的值
    private V cellValue ;

    private Date createTime ;

    @Override
    public String toString() {
        return "CellInfo{" +
                "cellId='" + cellId + '\'' +
                ", rowId='" + rowId + '\'' +
                ", headerName='" + headerName + '\'' +
                ", rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                ", columnName='" + columnName + '\'' +
                ", cellValue=" + cellValue +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getRowid() {
        return rowId;
    }

    public void setRowid(String rowId) {
        this.rowId = rowId;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public V getCellValue() {
        return cellValue;
    }

    public void setCellValue(V cellValue) {
        this.cellValue = cellValue;
    }
}

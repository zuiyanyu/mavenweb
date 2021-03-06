package com.utils.excel.domain;

import java.util.Date;
import java.util.List;

public class RowMeta {
    //row的主键
    private String rowId ;
    //row所在的sheet(sheet主键)
    private String sheetId ;
    //row在sheet中的索引/行号 从1开始
    private Integer rowIndex ;
    //row中的单元格信息
    private List<CellMeta> CellMetaList ;
    //row中单元格的数量
    private Integer rowCellCount ;

    private Date createTime;


    @Override
    public String toString() {
        return "RowInfo{" +
                "rowId='" + rowId + '\'' +
                ", sheetId='" + sheetId + '\'' +
                ", rowIndex=" + rowIndex +
                ", CellMetaList=" + CellMetaList +
                ", rowCellCount=" + rowCellCount +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRowCellCount() {
        return rowCellCount;
    }

    public void setRowCellCount(Integer rowCellCount) {
        this.rowCellCount = rowCellCount;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<CellMeta> getCellMetaList() {
        return CellMetaList;
    }

    public void setCellMetaList(List<CellMeta> CellMetaList) {
        this.CellMetaList = CellMetaList;
    }

}

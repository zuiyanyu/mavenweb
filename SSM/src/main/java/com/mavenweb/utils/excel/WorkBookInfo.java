package com.mavenweb.utils.excel;

import java.util.Date;
import java.util.List;

public class WorkBookInfo {
    //工作簿的主键
    private String workBookId ;
    //工作簿的文件名称
    private String workBookName ;
    //工作簿的sheet数量
    private Integer bookSheetCount;
    //创建日期
    private Date createTime ;

    //工作簿的sheet信息
    private List<SheetInfo> sheetInfoList ;

    @Override
    public String toString() {
        return "WorkBookInfo{" +
                "workBookId='" + workBookId + '\'' +
                ", workBookName='" + workBookName + '\'' +
                ", sheetInfoList=" + sheetInfoList +
                ", bookSheetCount=" + bookSheetCount +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getBookSheetCount() {
        return bookSheetCount;
    }

    public void setBookSheetCount(Integer bookSheetCount) {
        this.bookSheetCount = bookSheetCount;
    }

    public String getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(String workBookId) {
        this.workBookId = workBookId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWorkBookName() {
        return workBookName;
    }

    public void setWorkBookName(String workBookName) {
        this.workBookName = workBookName;
    }

    public List<SheetInfo> getSheetInfoList() {
        return sheetInfoList;
    }

    public void setSheetInfoList(List<SheetInfo> sheetInfoList) {
        this.sheetInfoList = sheetInfoList;
    }

}

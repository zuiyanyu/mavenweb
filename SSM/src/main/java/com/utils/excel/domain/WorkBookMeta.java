package com.utils.excel.domain;

import java.util.Date;
import java.util.List;

public class WorkBookMeta {
    //工作簿的主键
    private String workBookId ;
    //工作簿的文件名称
    private String workBookName ;
    //工作簿的sheet数量
    private Integer bookSheetCount;
    //创建日期
    private Date createTime ;

    //工作簿的sheet信息
    private List<SheetMeta> SheetMetaList ;

    @Override
    public String toString() {
        return "WorkBookInfo{" +
                "workBookId='" + workBookId + '\'' +
                ", workBookName='" + workBookName + '\'' +
                ", SheetMetaList=" + SheetMetaList +
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

    public List<SheetMeta> getSheetMetaList() {
        return SheetMetaList;
    }

    public void setSheetMetaList(List<SheetMeta> SheetMetaList) {
        this.SheetMetaList = SheetMetaList;
    }

}

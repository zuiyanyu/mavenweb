package com.mavenweb.domain;

import java.io.Serializable;
import java.util.Date;

public class UploadFile implements Serializable {
    // 表主键
    private Integer fileId;

    // 文件主键1
    private String fileId1;

    // 文件主键2
    private String fileId2;

    // 文件原始名称
    private String fileOriginName;

    // 文件扩展名
    private String fileExtendName;

    // 服务器上文件存储名名称
    private String fileStoredName;

    // 服务器上文件存储路径
    private String fileStoredPath;

    // 文件被哪一张表使用
    private String fileFromTab;

    // 文件上传日期
    private Date createTime;

    // 文件上传人
    private String creator;

    // 备用字段1
    private String attribute1;

    // 备用字段2
    private String attribute2;

    // 备用字段3
    private String attribute3;

     // tableName : upload_file
    private static final long serialVersionUID = 1L;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileId1() {
        return fileId1;
    }

    public void setFileId1(String fileId1) {
        this.fileId1 = fileId1;
    }

    public String getFileId2() {
        return fileId2;
    }

    public void setFileId2(String fileId2) {
        this.fileId2 = fileId2;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    public String getFileExtendName() {
        return fileExtendName;
    }

    public void setFileExtendName(String fileExtendName) {
        this.fileExtendName = fileExtendName;
    }

    public String getFileStoredName() {
        return fileStoredName;
    }

    public void setFileStoredName(String fileStoredName) {
        this.fileStoredName = fileStoredName;
    }

    public String getFileStoredPath() {
        return fileStoredPath;
    }

    public void setFileStoredPath(String fileStoredPath) {
        this.fileStoredPath = fileStoredPath;
    }

    public String getFileFromTab() {
        return fileFromTab;
    }

    public void setFileFromTab(String fileFromTab) {
        this.fileFromTab = fileFromTab;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileId1=").append(fileId1);
        sb.append(", fileId2=").append(fileId2);
        sb.append(", fileOriginName=").append(fileOriginName);
        sb.append(", fileExtendName=").append(fileExtendName);
        sb.append(", fileStoredName=").append(fileStoredName);
        sb.append(", fileStoredPath=").append(fileStoredPath);
        sb.append(", fileFromTab=").append(fileFromTab);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", attribute1=").append(attribute1);
        sb.append(", attribute2=").append(attribute2);
        sb.append(", attribute3=").append(attribute3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
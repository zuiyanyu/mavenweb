package com.mavenweb.domain;

public class DqReportItemLocal {
    private String   repItemId          ;
    private String   repItemName        ;
    private String   rpeName            ;
    private String   repSname           ;
    private String   rpeLineId          ;
    private String   repLineName        ;
    private String   repItemAddr        ;
    private String   rpeItemIndex       ;
    private String   repItemEquOrigin   ;
    private String   repItemEqu          ;
    private String   isok               ;

    public String getRepItemId() {
        return repItemId;
    }

    public void setRepItemId(String repItemId) {
        this.repItemId = repItemId;
    }

    public String getRepItemName() {
        return repItemName;
    }

    public void setRepItemName(String repItemName) {
        this.repItemName = repItemName;
    }

    public String getRpeName() {
        return rpeName;
    }

    public void setRpeName(String rpeName) {
        this.rpeName = rpeName;
    }

    public String getRepSname() {
        return repSname;
    }

    public void setRepSname(String repSname) {
        this.repSname = repSname;
    }

    public String getRpeLineId() {
        return rpeLineId;
    }

    public void setRpeLineId(String rpeLineId) {
        this.rpeLineId = rpeLineId;
    }

    public String getRepLineName() {
        return repLineName;
    }

    public void setRepLineName(String repLineName) {
        this.repLineName = repLineName;
    }

    public String getRepItemAddr() {
        return repItemAddr;
    }

    public void setRepItemAddr(String repItemAddr) {
        this.repItemAddr = repItemAddr;
    }

    public String getRpeItemIndex() {
        return rpeItemIndex;
    }

    public void setRpeItemIndex(String rpeItemIndex) {
        this.rpeItemIndex = rpeItemIndex;
    }

    public String getRepItemEquOrigin() {
        return repItemEquOrigin;
    }

    public void setRepItemEquOrigin(String repItemEquOrigin) {
        this.repItemEquOrigin = repItemEquOrigin;
    }

    public String getRepTemEqu() {
        return repItemEqu;
    }

    public void setRepTemEqu(String repTemEqu) {
        this.repItemEqu = repTemEqu;
    }

    public String getIsok() {
        return isok;
    }

    public void setIsok(String isok) {
        this.isok = isok;
    }

    @Override
    public String toString() {
        return "DqReportItemLocal{" +
                "repItemId='" + repItemId + '\'' +
                ", repItemName='" + repItemName + '\'' +
                ", rpeName='" + rpeName + '\'' +
                ", repSname='" + repSname + '\'' +
                ", rpeLineId='" + rpeLineId + '\'' +
                ", repLineName='" + repLineName + '\'' +
                ", repItemAddr='" + repItemAddr + '\'' +
                ", rpeItemIndex='" + rpeItemIndex + '\'' +
                ", repItemEquOrigin='" + repItemEquOrigin + '\'' +
                ", repTemEqu='" + repItemEqu + '\'' +
                ", isok='" + isok + '\'' +
                '}';
    }
}

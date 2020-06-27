package com.mavenweb.domain;

public class RepItemEqu {
    private String  repItemId            ;
    private String  rpeName              ;
    private String  repLineName          ;
    private String  repItemName          ;
    private String  repSname             ;
    private String  repItemAddr          ;
    private String  repItemEquOrigin    ;
    private String  repItemEqu           ;
    private String  ext                  ;

    public String getRepItemEquOrigin() {
        return repItemEquOrigin;
    }

    public void setRepItemEquOrigin(String repItemEquOrigin) {
        this.repItemEquOrigin = repItemEquOrigin;
    }

    @Override
    public String toString() {
        return "RepItemEqu{" +
                "repItemId='" + repItemId + '\'' +
                ", rpeName='" + rpeName + '\'' +
                ", repLineName='" + repLineName + '\'' +
                ", repItemName='" + repItemName + '\'' +
                ", repSname='" + repSname + '\'' +
                ", repItemAddr='" + repItemAddr + '\'' +
                ", repItemEqu_origin='" + repItemEquOrigin + '\'' +
                ", repItemEqu='" + repItemEqu + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }

    public String getRepItemId() {
        return repItemId;
    }

    public void setRepItemId(String repItemId) {
        this.repItemId = repItemId;
    }

    public String getRpeName() {
        return rpeName;
    }

    public void setRpeName(String rpeName) {
        this.rpeName = rpeName;
    }

    public String getRepLineName() {
        return repLineName;
    }

    public void setRepLineName(String repLineName) {
        this.repLineName = repLineName;
    }

    public String getRepItemName() {
        return repItemName;
    }

    public void setRepItemName(String repItemName) {
        this.repItemName = repItemName;
    }

    public String getRepSname() {
        return repSname;
    }

    public void setRepSname(String repSname) {
        this.repSname = repSname;
    }

    public String getRepItemAddr() {
        return repItemAddr;
    }

    public void setRepItemAddr(String repItemAddr) {
        this.repItemAddr = repItemAddr;
    }



    public String getRepItemEqu() {
        return repItemEqu;
    }

    public void setRepItemEqu(String repItemEqu) {
        this.repItemEqu = repItemEqu;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}

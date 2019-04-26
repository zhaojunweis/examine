package com.examine.domain;

import java.io.Serializable;
import java.util.Date;

public class TSubmit implements Serializable {

    private long id;

    private String sSno;

    private Date createTime;

    private String examPaper;

    private String ipAddress;

    private int sIsDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getsSno() {
        return sSno;
    }

    public void setsSno(String sSno) {
        this.sSno = sSno;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(String examPaper) {
        this.examPaper = examPaper;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getsIsDelete() {
        return sIsDelete;
    }

    public void setsIsDelete(int sIsDelete) {
        this.sIsDelete = sIsDelete;
    }
}

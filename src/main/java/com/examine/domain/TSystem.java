package com.examine.domain;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;

public class TSystem implements Serializable {

    private long id;

    private String sExamTime;

    private int sPageCount;

    private long maxUploadSize;

    private long minUploadSize;

    private String manualOpenTime;

    private int sCanDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getsExamTime() {
        return sExamTime;
    }

    public void setsExamTime(String sExamTime) {
        this.sExamTime = sExamTime;
    }

    public int getsPageCount() {
        return sPageCount;
    }

    public void setsPageCount(int sPageCount) {
        this.sPageCount = sPageCount;
    }

    public long getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public long getMinUploadSize() {
        return minUploadSize;
    }

    public void setMinUploadSize(long minUploadSize) {
        this.minUploadSize = minUploadSize;
    }

    public String getManualOpenTime() {
        return manualOpenTime;
    }

    public void setManualOpenTime(String manualOpenTime) {
        this.manualOpenTime = manualOpenTime;
    }

    public int getsCanDelete() {
        return sCanDelete;
    }

    public void setsCanDelete(int sCanDelete) {
        this.sCanDelete = sCanDelete;
    }
}

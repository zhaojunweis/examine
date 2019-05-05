package com.examine.domain;

import java.io.Serializable;

public class TExam implements Serializable {

    private long id;

    private String examName;

    private String examStartTime;

    private String tName;

    private String examPaperUrl;

    private int isAutoStart;

    private int isStart;

    private int isPigeonhole;

    private int isDelete;

    private int isFinished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getExamPaperUrl() {
        return examPaperUrl;
    }

    public void setExamPaperUrl(String examPaperUrl) {
        this.examPaperUrl = examPaperUrl;
    }

    public int getIsAutoStart() {
        return isAutoStart;
    }

    public void setIsAutoStart(int isAutoStart) {
        this.isAutoStart = isAutoStart;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public int getIsPigeonhole() {
        return isPigeonhole;
    }

    public void setIsPigeonhole(int isPigeonhole) {
        this.isPigeonhole = isPigeonhole;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }
}

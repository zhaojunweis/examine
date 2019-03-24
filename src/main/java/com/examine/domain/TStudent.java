package com.examine.domain;

public class TStudent {

    private long id;

    private String sSno;

    private String sName;

    private String sPass;

    private int sClassId;

    private String sScoreName;

    private int sIsDelete;

    public TStudent() {
    }

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

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public int getsClassId() {
        return sClassId;
    }

    public void setsClassId(int sClassId) {
        this.sClassId = sClassId;
    }

    public String getsScoreName() {
        return sScoreName;
    }

    public void setsScoreName(String sScoreName) {
        this.sScoreName = sScoreName;
    }

    public int getsIsDelete() {
        return sIsDelete;
    }

    public void setsIsDelete(int sIsDelete) {
        this.sIsDelete = sIsDelete;
    }
}

package com.examine.domain;

public class TTeacher {

    private long id;

    private String tName;

    private String tPass;

    private int tIsAdmin;

    private int tIsDelete;

    public TTeacher() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPass() {
        return tPass;
    }

    public void settPass(String tPass) {
        this.tPass = tPass;
    }

    public int gettIsAdmin() {
        return tIsAdmin;
    }

    public void settIsAdmin(int tIsAdmin) {
        this.tIsAdmin = tIsAdmin;
    }

    public int gettIsDelete() {
        return tIsDelete;
    }

    public void settIsDelete(int tIsDelete) {
        this.tIsDelete = tIsDelete;
    }
}
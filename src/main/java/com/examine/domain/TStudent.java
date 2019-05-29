package com.examine.domain;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TStudent implements Serializable {

    private long id;

    private String sSno;

    private String sName;

    private int sClassId;

    private int sScoreId;

    private int sIsDelete;

    private String lastSubmit;

    private String roleId;

    private TRole role;

    private TSubmit tSubmit;

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

    public int getsClassId() {
        return sClassId;
    }

    public void setsClassId(int sClassId) {
        this.sClassId = sClassId;
    }

    public int getScoreId() {
        return sScoreId;
    }

    public void setScoreId(Integer sScoreId) {
        this.sScoreId = sScoreId;
    }

    public int getsIsDelete() {
        return sIsDelete;
    }

    public void setsIsDelete(int sIsDelete) {
        this.sIsDelete = sIsDelete;
    }

    public String getLastSubmit() {
        return lastSubmit;
    }

    public void setLastSubmit(String lastSubmit) {
        this.lastSubmit = lastSubmit;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }

    public void settSubmit(TSubmit tSubmit) {
        this.tSubmit = tSubmit;
    }

    public TSubmit gettSubmit() {
        return tSubmit;
    }
}

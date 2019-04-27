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

    private String sScoreName;

    private int sIsDelete;

    private String lastSubmit;

    Set<Role> roleSet = new HashSet<>();

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

    public String getLastSubmit() {
        return lastSubmit;
    }

    public void setLastSubmit(String lastSubmit) {
        this.lastSubmit = lastSubmit;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}

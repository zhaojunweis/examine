package com.examine.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class TRole implements Serializable {

    private int roleId;

    private String role;

    private String description;

    Set<TPerm> permSet = new HashSet<>();

    public int getId() {
        return roleId;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TPerm> getPermSet() {
        return permSet;
    }

    public void setPermSet(Set<TPerm> permSet) {
        this.permSet = permSet;
    }
}

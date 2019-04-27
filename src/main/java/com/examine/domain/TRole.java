package com.examine.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class TRole implements Serializable {

    private int id;

    private String role;

    private String description;

    Set<TPerm> permSet = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

package com.examine.domain;

import java.io.Serializable;

public class TPerm implements Serializable {

    private int permId;

    private String url;

    private String desc;

    public int getId() {
        return permId;
    }

    public void setId(int id) {
        this.permId = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

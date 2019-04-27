package com.examine.domain;

import java.io.Serializable;

public class TNotification implements Serializable {

    private long id;

    private String message;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

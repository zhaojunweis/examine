package com.examine.service;

public interface SubmitService {
    void insertStudentLoginMessage(String sName,String ipAddress);

    boolean nonphysicalDeleteAllSubmit();
}

package com.examine.service;

import java.util.List;

public interface SubmitService {
    void insertStudentLoginMessage(String sName,String ipAddress);

    boolean nonphysicalDeleteAllSubmit();

    List<String> downloadSubmitZip();
}

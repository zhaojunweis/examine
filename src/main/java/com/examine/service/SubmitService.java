package com.examine.service;

import java.util.List;

public interface SubmitService {
    void insertStudentLoginMessage(String sName,String ipAddress);

    boolean nonphysicalDeleteAllSubmit(Integer Id);

    List<String> downloadSubmitZip();

    Integer doUnbinding(String sSno);

    boolean finishedPageOnHole(Integer id);
}

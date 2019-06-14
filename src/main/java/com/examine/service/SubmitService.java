package com.examine.service;

import com.examine.domain.TSubmit;

import java.util.List;
import java.util.Map;

public interface SubmitService {
    void insertStudentLoginMessage(String sName,String ipAddress,Integer examId);

    boolean nonphysicalDeleteAllSubmit(Integer Id);

    List<String> downloadSubmitZip();

    Integer doUnbinding(String sSno);

    boolean finishedPageOnHole(Integer id);

    List<TSubmit> selectSubmitResult(Map map);

    TSubmit selectSubmitEntity(Map map);

    boolean updateSubmitStudentIP(Map map);
}

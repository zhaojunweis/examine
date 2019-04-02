package com.examine.service;


import com.examine.domain.TSystem;

public interface SystemService {
    boolean updateSystemConfigure(TSystem system);

    TSystem selectSystemConfigure();

    Long selectMaxUploadSize();

    Long selectMinUploadSize();

    Integer selectExamTime();

    Integer selectPageCount();

    Integer selectManualOpenTime();
}

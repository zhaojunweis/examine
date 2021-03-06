package com.examine.service;


import com.examine.domain.TSystem;

public interface SystemService {
    boolean updateSystemConfigure(TSystem system);

    TSystem selectSystemConfigure();

    long selectMaxUploadSize();

    long selectMinUploadSize();

    int selectSystemTimer();
}

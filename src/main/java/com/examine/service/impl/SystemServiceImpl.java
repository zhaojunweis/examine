package com.examine.service.impl;

import com.examine.dao.SystemMapper;
import com.examine.domain.TSystem;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    private final SystemMapper systemMapper;

    @Autowired
    public SystemServiceImpl(SystemMapper systemMapper) {
        this.systemMapper = systemMapper;
    }

    @Override
    public boolean insertSystemConfigure(TSystem system) {
        boolean flag = false;
        int insertCount = systemMapper.insertSystemConfigure(system);
        if (insertCount == 1) {
            flag = true;
        }
        return flag;
    }
}

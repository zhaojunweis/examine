package com.examine.service.impl;

import com.examine.dao.SubmitMapper;
import com.examine.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    private SubmitMapper submitMapper;

    @Override
    public void insertStudentLoginMessage(String sName, String ipAddress) {
        Map<String,String> loginMessage = new HashMap<>();
        loginMessage.put("sName",sName);
        loginMessage.put("ipAddress",ipAddress);
        submitMapper.insertStudentLoginMessage(loginMessage);
    }
}

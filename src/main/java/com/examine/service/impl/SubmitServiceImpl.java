package com.examine.service.impl;

import com.examine.dao.SubmitMapper;
import com.examine.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubmitServiceImpl implements SubmitService {

    private final SubmitMapper submitMapper;

    @Autowired
    public SubmitServiceImpl(SubmitMapper submitMapper) {
        this.submitMapper = submitMapper;
    }

    /**
     * 插入登陆学生登陆信息到t_submit
     *
     * @param sName
     * @param ipAddress
     */
    @Override
    public void insertStudentLoginMessage(String sName, String ipAddress) {
        Map<String, String> loginMessage = new HashMap<>();
        loginMessage.put("sName", sName);
        loginMessage.put("ipAddress", ipAddress);
        submitMapper.insertStudentLoginMessage(loginMessage);
    }

    /**
     * 非物理删除所有提交
     *
     * @return
     */
    @Override
    public boolean nonphysicalDeleteAllSubmit() {
        boolean flag = false;
        Integer affectCount = submitMapper.nonphysicalDeleteAllSubmit();
        if (affectCount >= 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<String> downloadSubmitZip() {
        return submitMapper.downloadSubmitZip();
    }
}

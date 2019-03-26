package com.examine.service.impl;

import com.examine.dao.ExamMapper;
import com.examine.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;

    @Autowired
    public ExamServiceImpl(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    @Override
    public boolean nonphysicalDeleteOneExamByName(String examName) {
        boolean flag = false;
        int affectCount = examMapper.nonphysicalDeleteOneExamByName(examName);
        if (affectCount >= 1) {
            flag = true;
        }
        return flag;
    }
}

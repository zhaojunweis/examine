package com.examine.service.impl;

import com.examine.dao.TeacherMapper;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public String selectTeacherPasswordByUsername(String tName) {
        return null;
    }
}

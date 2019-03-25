package com.examine.service.impl;

import com.examine.dao.StudentMapper;
import com.examine.domain.TStudent;
import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String selectStudentPasswordByUsername(String sSno) {
        return studentMapper.selectStudentPasswordByUsername(sSno);
    }

    @Override
    public String selectIpAddressByUsername(String sSno) {
        return studentMapper.selectIpAddressByUsername(sSno);
    }

    @Override
    public TStudent selectStudentEntityByUsername(String sSno) {
        return studentMapper.selectStudentEntityByUsername(sSno);
    }
}

package com.examine.service.impl;

import com.examine.dao.StudentMapper;
import com.examine.domain.TTeacher;
import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<TTeacher> selectAllUser() {
        return null;
    }
}

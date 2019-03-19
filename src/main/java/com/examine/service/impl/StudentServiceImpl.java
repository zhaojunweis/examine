package com.examine.service.impl;

import com.examine.dao.StudentMapper;
import com.examine.domain.TTeacher;
import com.examine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentMapper userMapperTest;

    @Override
    public List<TTeacher> selectAllUser() {
       return userMapperTest.selectAllUser();
    }
}

package com.examine.service.impl;

import com.examine.dao.UserMapperTest;
import com.examine.domain.User;
import com.examine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperTest userMapperTest;

    @Override
    public List<User> selectAllUser() {
       return userMapperTest.selectAllUser();
    }
}

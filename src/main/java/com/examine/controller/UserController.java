package com.examine.controller;

import com.examine.domain.User;
import com.examine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user")
    @ResponseBody
    public List<User> getAllUser(){
       return userService.selectAllUser();
    }
}

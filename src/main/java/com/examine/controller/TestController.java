package com.examine.controller;

import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getTeacherPerm")
    @ResponseBody
    public TTeacher getTeacherPerm(){

        String tName = "zjw";
        return teacherService.selectTeacherRoleAndPerm(tName);
    }

}

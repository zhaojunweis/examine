package com.examine.controller;

import com.examine.domain.TTeacher;
import com.examine.service.ExamService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExamService examService;

    @RequestMapping("/getTeacherPerm")
    @ResponseBody
    public TTeacher getTeacherPerm() {

        String tName = "zjw";
        return teacherService.selectTeacherRoleAndPerm(tName);
    }

    @RequestMapping("/finishedExam")
    @ResponseBody
    public boolean finishedExam() {
        int TMP = 58;
        return examService.stopOneExamById(TMP);
    }

}

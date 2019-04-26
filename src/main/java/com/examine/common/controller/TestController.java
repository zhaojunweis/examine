package com.examine.common.controller;

import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @Auther:wencaixu
 * @Date:2019/4/25
 * @Description:com.examine.common.controller
 */
@Controller
public class TestController {

    @Autowired
    private StudentService studentServicel;

    @RequestMapping("/studentOnLine")
    @ResponseBody
    public Integer getStudentOnLine(){

        return studentServicel.examineesCount("Java");
    }

}

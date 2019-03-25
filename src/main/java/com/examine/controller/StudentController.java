package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.IpUtil;
import com.examine.common.util.StringUtils;
import com.examine.domain.TStudent;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class StudentController extends BaseController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    private final SubmitService submitService;

    @Autowired
    public StudentController(SubmitService submitService, StudentService studentService) {
        this.submitService = submitService;
        this.studentService = studentService;
    }

    @RequestMapping(value = "/submitStudentLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(TStudent student, HttpSession session) {
        TStudent tStudent;
        logger.info(student.getsSno() + " " + student.getsPass());
        String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
        if (StringUtils.isBlank(loginIp)) {
            String ip = IpUtil.getLocalIp();
            tStudent = studentService.selectStudentEntityByUsername(student.getsSno());
            if (tStudent.getsPass() != null) {
                if (student.getsPass().equals(tStudent.getsPass())) {
                    session.setAttribute("student", tStudent);
                    submitService.insertStudentLoginMessage(tStudent.getsSno(), ip);
                    resultMap.put("status", 200);
                    resultMap.put("url", "success");
                    resultMap.put("message", "login success");
                } else {
                    resultMap.put("status", 404);
                    resultMap.put("message", "wrong password,Not Found");
                }
            } else {
                resultMap.put("message", "this user is not exist");
            }
        } else {
            resultMap.put("status", 403);
            resultMap.put("message", "you ip address has been used,Forbidden");
        }
        return resultMap;
    }
}

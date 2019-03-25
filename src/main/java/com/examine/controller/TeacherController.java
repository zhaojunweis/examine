package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.StringUtils;
import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class TeacherController extends BaseController {

    @Autowired
    public TeacherService teacherService;

    @RequestMapping("/submitTeacherLogin")
    @ResponseBody
    public Map<String, Object> submitTeacherLogin(TTeacher tTeacher, HttpSession session) {
        String password = teacherService.selectTeacherPasswordByUsername(tTeacher.gettName());
        if (!StringUtils.isBlank(password, tTeacher.gettPass())) {
            if (password.equals(tTeacher.gettPass())) {
                session.setAttribute("tName", tTeacher.gettName());
                resultMap.put("status", "200");
                resultMap.put("url", "/teacher");
                resultMap.put("message", "welcome to teacher");
            } else {
                resultMap.put("message", "wrong password");
            }
        } else {
            resultMap.put("status", 404);
            resultMap.put("message", "not exist!");
        }
        return resultMap;
    }

    @RequestMapping("/submitAdminLogin")
    @ResponseBody
    public Map<String, Object> submitAdminLogin(
            @RequestParam(defaultValue = "admin") String tName,
            @RequestParam(defaultValue = "admin") String tPass,
            HttpSession session) {
        if ("admin".equals(tName)) {
            if ("admin".equals(tPass)) {
                resultMap.put("status", 200);
                resultMap.put("url", "admin_page");
                resultMap.put("message", "login success");
            }
        } else {
            String password = teacherService.selectAdminByLoginMessage(tName);
            if (tPass.equals(password)) {
                resultMap.put("status", 200);
                resultMap.put("url", "admin_page");
                resultMap.put("message", "login success");
            } else {
                resultMap.put("message", "wrong password");
            }
        }
        session.setAttribute("tName", tName);
        return resultMap;
    }


}

package com.examine.common.controller;

import com.examine.common.util.StringUtils;
import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @version 1.0
 * @Auther:wencaixu
 * @Date:2019/5/3
 * @Description:com.examine.common.controller
 */

@Controller
public class TeacherLoginController extends BaseController {


    @Autowired
    private  TeacherService teacherService;

    /**
     * 教师登陆
     *
     * @param tTeacher
     * @param session
     * @return
     */
    @RequestMapping("/submitTeacherLoginTest")
    @ResponseBody
    public Map<String, Object> submitTeacherLogin(TTeacher tTeacher, HttpSession session) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(tTeacher.gettName(),tTeacher.gettPass());

        try {
            subject.login(token);
            session.setAttribute("tName", tTeacher.gettName());
            resultMap.put("status", "200");
            resultMap.put("url", "/teacher_main");
            resultMap.put("message", "welcome to teacher");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap.put("status", 404);
            resultMap.put("message", "not exist!");
        }

        /*String password = teacherService.selectTeacherPasswordByUsername(tTeacher.gettName());
        if (!StringUtils.isBlank(password, tTeacher.gettPass())) {
            if (password.equals(tTeacher.gettPass())) {
                session.setAttribute("tName", tTeacher.gettName());
                resultMap.put("status", "200");
                resultMap.put("url", "/teacher_main");
                resultMap.put("message", "welcome to teacher");
            } else {
                resultMap.put("message", "wrong password");
            }
        } else {
            resultMap.put("status", 404);
            resultMap.put("message", "not exist!");
        }*/
        return resultMap;
    }

}

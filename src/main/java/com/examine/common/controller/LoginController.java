package com.examine.common.controller;

import com.examine.common.util.IpUtil;
import com.examine.common.util.StringUtils;
import com.examine.controller.StudentController;
import com.examine.domain.TStudent;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @version 1.0
 * @Auther:wencaixu
 * @Date:2019/4/28
 * @Description:com.examine.common.controller
 */

@Controller
public class LoginController extends BaseController{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final StudentService studentService;

    private final SubmitService submitService;

    @Autowired
    public LoginController(SubmitService submitService, StudentService studentService) {
        this.submitService = submitService;
        this.studentService = studentService;
    }


    @RequestMapping(value = "/submitStudentLoginTest", method = RequestMethod.POST)
    @ResponseBody
    public Object submitLogin(TStudent student, HttpSession session) {

        //获取实体
        Subject subject = SecurityUtils.getSubject();

        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken(student.getsSno(),student.getsName());

        //认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap.put("status", 500);
            resultMap.put("message", "password or username wrong");
            return resultMap;
        }

        //获取对象实体
        TStudent tStudent = (TStudent) subject.getPrincipal();

        //boolean admin = subject.hasRole("student");

        TStudent sStudent = studentService.selectStudentEntityByUsername(student.getsSno());

        System.out.println(((TStudent) subject.getPrincipal()).getsName());

        if(sStudent != null){
            String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
            String localIp = IpUtil.getLocalIp();
            if(StringUtils.isBlank(loginIp) || loginIp.equals(localIp)){
                session.setAttribute("student", tStudent);
                //更新或插入学生信息到t_submit
                /*if(){

                }*/
                return new ModelAndView("success");
              /*  resultMap.put("status", 200);
                resultMap.put("url", "success");
                resultMap.put("message", "login success");*/
            }else{
                resultMap.put("status", 403);
                resultMap.put("message", "you ip address has been used,Forbidden");
            }
        }

       /* TStudent tStudent;
        tStudent = studentService.selectStudentEntityByUsername(student.getsSno());
        logger.info(student.getsSno() + " " + student.getsName());
        if (tStudent != null) {
            String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
            String ip = IpUtil.getLocalIp();
            if (StringUtils.isBlank(loginIp) || loginIp.equals(ip)) {
                if (student.getsName().equals(tStudent.getsName())) {
                    session.setAttribute("student", tStudent);
                    //如果登陆Ip为空，则插入，否者不变
                    if(StringUtils.isBlank(loginIp)){
                        submitService.insertStudentLoginMessage(tStudent.getsSno(), ip);
                    }
                    resultMap.put("status", 200);
                    resultMap.put("url", "success");
                    resultMap.put("message", "login success");
                } else {
                    resultMap.put("status", 404);
                    resultMap.put("message", "wrong password,Not Found");
                }

            } else {
                resultMap.put("status", 403);
                resultMap.put("message", "you ip address has been used,Forbidden");
            }
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "this user is not exist");
        }

        return resultMap;*/
       return resultMap;
    }

}

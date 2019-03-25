package com.examine.common.controller;

import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController extends BaseController {

    @Autowired
    private TeacherService teacherService;
    /**
     * logout
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
       session.invalidate();
       return new ModelAndView("redirect:/index");
    }


    /**
     * change password
     * @param tTeacher
     * @return
     */
    @RequestMapping("/editPassword")
    public Map<String,Object> editPassword(TTeacher tTeacher){
        Map<String,Object> parameterMap = new HashMap<>();
        parameterMap.put("tName",tTeacher.gettName());
        parameterMap.put("tPass",tTeacher.gettPass());
        teacherService.updateAccountByUsername(parameterMap);
        resultMap.put("status",200);
        resultMap.put("message","update success");
        return resultMap;
    }

}

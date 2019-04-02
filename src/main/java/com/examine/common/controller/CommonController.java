package com.examine.common.controller;

import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController extends BaseController {

    private final TeacherService teacherService;

    @Autowired
    public CommonController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/editPassword")
    @ResponseBody
    public Map<String,Object> editPassword(TTeacher tTeacher){
        Map<String,Object> parameterMap = new HashMap<>();
        parameterMap.put("tName",tTeacher.gettName());
        parameterMap.put("tPass",tTeacher.gettPass());
        if(teacherService.updateAccountByUsername(parameterMap)==1){
            resultMap.put("status",200);
            resultMap.put("message","update success");
        }else{
            resultMap.put("status",500);
            resultMap.put("message","update error");
        }

        return resultMap;
    }
    @RequestMapping("/checkPasswordByname")
    @ResponseBody
    public Map<String,Object> checkPasswordByname(@RequestParam(value = "tName") String name){
        Map<String ,Object> parameterMap = new HashMap<>();
        String pd = teacherService.selectTeacherPasswordByUsername(name);
        if(!pd.equals(null)){
            resultMap.put("status",200);
            resultMap.put("content",pd);
        }else {
            resultMap.put("status",500);
        }
        return resultMap;
    }
}

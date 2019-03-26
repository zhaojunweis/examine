package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AdministratorController extends BaseController {

    private final TeacherService teacherService;

    @Autowired
    public AdministratorController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * admin login
     * @param tName
     * @param tPass
     * @param session
     * @return
     */
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

    /**
     * add teacher
     * @param tTeacher
     * @return
     */
    @RequestMapping("/saveTeacher")
    @ResponseBody
    public Map<String, Object> saveTeacher(TTeacher tTeacher) {
        boolean flag = teacherService.saveTeacher(tTeacher);
        if (flag) {
            List<TTeacher> teachers = teacherService.selectAllTeacher();
            resultMap.put("message", teachers);
        } else {
            resultMap.put("message", "save teacher failed!");
        }
        return resultMap;
    }

    /**
     * return add teacher viewName
     * @param tName
     * @return
     */
    @RequestMapping("/removeTeacher")
    public ModelAndView removeTeacher(String tName) {
        ModelAndView mv = new ModelAndView();
        teacherService.removeTeacher(tName);
        List<TTeacher> teachers = teacherService.selectAllTeacher();
        mv.addObject("teachers", teachers);
        return new ModelAndView("");
    }
}
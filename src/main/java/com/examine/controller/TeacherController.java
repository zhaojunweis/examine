package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.ExcelUtils;
import com.examine.common.util.StringUtils;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.StudentService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController extends BaseController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

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

    @RequestMapping("/importStudentInfo")
    @ResponseBody
    public Map<String,Object> importStudentInfo(String localExcelPath){
        boolean importStatus = studentService.importStudentInfo(localExcelPath);
        if(!importStatus){
            resultMap.put("status","500");
            resultMap.put("message","import failed");
        }
        resultMap.put("status","200");
        resultMap.put("message","import success");
        return resultMap;
    }

    @RequestMapping("/exportSubmitInfo")
    public void exportSubmitInfo(HttpServletResponse response){
        String[] title = {"班级", "学号", "姓名","最后提交时间"};
        //获取学生信息
        List<TStudent> students = teacherService.exportSubmitInfo();
        ExcelUtils excel = new ExcelUtils(title);
        try {
            excel.buildExcelDocument(students,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

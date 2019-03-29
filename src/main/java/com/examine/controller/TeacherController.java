package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.ExcelUtils;
import com.examine.common.util.StringUtils;
import com.examine.common.util.ZipUtils;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

@Controller
public class TeacherController extends BaseController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final SubmitService submitService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService, SubmitService submitService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.submitService = submitService;
    }

    /**
     * 教师登陆
     *
     * @param tTeacher
     * @param session
     * @return
     */
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

    /**
     * 导入学生信息到数据库中
     *
     * @param localExcelPath
     * @return
     */
    @RequestMapping("/importStudentInfo")
    @ResponseBody
    public Map<String, Object> importStudentInfo(String localExcelPath) {
        boolean importStatus = studentService.importStudentInfo(localExcelPath);
        if (!importStatus) {
            resultMap.put("status", "500");
            resultMap.put("message", "import failed");
        }
        resultMap.put("status", "200");
        resultMap.put("message", "import success");
        return resultMap;
    }

    /**
     * 导出提交信息
     *
     * @param response
     */
    @RequestMapping("/exportSubmitInfo")
    public void exportSubmitInfo(HttpServletResponse response) {
        String[] title = {"班级", "学号", "姓名", "最后提交时间"};
        //获取学生信息
        List<TStudent> students = teacherService.exportSubmitInfo();
        ExcelUtils excel = new ExcelUtils(title);
        try {
            excel.buildExcelDocument(students, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载所有学生提交的内容
     *
     * @param response
     */
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response) {
        String zipName = UUID.randomUUID().toString() + ".zip";
        List<String> fileList = submitService.downloadSubmitZip();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment;filename=" + zipName);
        try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            for (String aFileList : fileList) {
                ZipUtils.doCompress(aFileList, out);
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 教师手动添加学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/saveStudent")
    @ResponseBody
    public Map<String, Object> saveStudent(TStudent student) {
        boolean flag = studentService.insertStudent(student);
        if (!flag) {
            resultMap.put("status", 500);
            resultMap.put("message", "insert failed");
        }
        resultMap.put("status", 200);
        resultMap.put("message", "insert success");
        return resultMap;
    }


    /**
     * 更新学生脏数据
     *
     * @param student
     * @return
     */
    @RequestMapping("/updateDirtyData")
    @ResponseBody
    public Map<String,Object> updateDirtyData(TStudent student){
        boolean flag = studentService.updateDirtyData();
        if(!flag){
            resultMap.put("status", 500);
            resultMap.put("message", "update failed");
        }
        resultMap.put("status", 200);
        resultMap.put("message", "update success");
        return resultMap;
    }

}

package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TExam;
import com.examine.service.ExamService;
import com.examine.service.SavePaperService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class ExamController extends BaseController {

    private final TeacherService teacherService;

    private final ExamService examService;

    private final SavePaperService savePaperService;

    @Autowired
    public ExamController(TeacherService teacherService, ExamService examService, SavePaperService savePaperService) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.savePaperService = savePaperService;
    }

    @RequestMapping("/clearExam")
    @ResponseBody
    public Map<String, Object> clearExam(String examName) {
        boolean isSuccess = teacherService.clearExamInfo(examName);
        if (isSuccess) {
            resultMap.put("status", 200);
            resultMap.put("message", "delete failed");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "delete exam failed");
        }
        return resultMap;
    }

    @RequestMapping("/saveExam")
    public void saveExam(TExam exam, HttpSession session) {
        //考试信息中包括老师信息
        String tName = (String) session.getAttribute("tName");
        exam.settName(tName);
        examService.saveExaminationInfo(exam);
    }

    @RequestMapping("/uploadExamPaper")
    public void uploadExamPaper(String examName,MultipartFile multipartFile,HttpSession session){
        session.setAttribute("examName",examName);
        try {
            savePaperService.SavePaperService(multipartFile,session);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/stopExam")
    public Map<String,Object> stopExam(String examName){
        boolean status = examService.stopExam(examName);

        if(!status){
            resultMap.put("status",500);
        }
        resultMap.put("status",200);
        return resultMap;
    }
}

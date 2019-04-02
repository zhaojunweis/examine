package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TExam;
import com.examine.service.ExamService;
import com.examine.service.PaperService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
public class ExamController extends BaseController {

    private final TeacherService teacherService;

    private final ExamService examService;

    private final PaperService paperService;

    @Autowired
    public ExamController(TeacherService teacherService, ExamService examService, PaperService paperService) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.paperService = paperService;
    }

    /**
     * 清除考试
     *
     * @param examName
     * @return
     */
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

    /**
     * 保存考试信息
     *
     * @param exam
     * @param session
     */
    @RequestMapping("/saveExam")
    public void saveExam(TExam exam, HttpSession session) {
        //考试信息中包括老师信息
        String tName = (String) session.getAttribute("tName");
        exam.settName(tName);
        examService.saveExaminationInfo(exam);
    }

    /**
     * 教师上传考试试卷
     * 需要传入考试名称参数，修改后台数据库的考试试题卷的url
     *
     * @param examName
     * @param multipartFile
     * @param session
     */
    @RequestMapping("/uploadExamPaper")
    public void uploadExamPaper(String examName,MultipartFile multipartFile,HttpSession session){
        session.setAttribute("examName",examName);
        try {
            paperService.SavePaperService(multipartFile,session);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭考试
     *
     * @param examName
     * @return
     */
    @RequestMapping("/stopExam")
    public Map<String,Object> stopExam(String examName){
        boolean status = examService.stopExam(examName);

        if(!status){
            resultMap.put("status",500);
        }
        resultMap.put("status",200);
        return resultMap;
    }

    /**
     * 查询所有考试信息
     *
     * @return
     */
    @RequestMapping("/selectAllExamInfo")
    @ResponseBody
    public List<TExam> selectAllExamInfo(){

        return examService.selectAllExamInfo();
    }
}

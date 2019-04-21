package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.controller.CommonController;
import com.examine.domain.TExam;
import com.examine.domain.TSystem;
import com.examine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ExamController extends BaseController {

    private final TeacherService teacherService;

    private final ExamService examService;

    private final PaperService paperService;

    private final SystemService systemService;
     private final CommonController commonController;

    @Autowired
    public ExamController(TeacherService teacherService, ExamService examService, PaperService paperService,SystemService systemService,CommonController commonController) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.paperService = paperService;
        this.systemService = systemService;
        this.commonController = commonController;
    }


   

    /*
    *  教师在考前管理界面进入的
    *  考试编辑界面修改考试信息
    * */
    @RequestMapping(value = "/examinfo_modifier")
    @ResponseBody
    public Map<String,Object> examinfo_modifier(TExam tExame){
        int id = (int)tExame.getId();
        TExam exam = examService.selectOneExamInfoById(id);
        if(exam.getExamName().equals(tExame.getExamName())&&exam.getExamStartTime().equals(tExame.getExamStartTime())){
            resultMap.put("status",200);
            resultMap.put("message","考试信息未变动，无需修改");
        }else{
            boolean tag = examService.updateExamInfo(tExame);
            if(tag){
                resultMap.put("status",200);
                resultMap.put("message","您的考试信息修改成功");
            }else{
                resultMap.put("status",500);
                resultMap.put("massage","考试信息修改失败，请重试");
            }
        }

        return resultMap;

    }


    /*
     * 考试清理界面初始化
     */
    @RequestMapping(value="/admin_exam")
    public ModelAndView admin_exam() throws ParseException {
        ModelAndView mv = new ModelAndView();
       List<Map> listmap  = commonController.getExamineInfo();
        mv.addObject("examlists",listmap);
        mv.setViewName("/admin_exam");
        return mv;
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

    /*
    * 添加考试
    *
    * */
    @RequestMapping("/saveExam")
    public Object saveExam(TExam exam, HttpSession session) {
        //考试信息中包括老师信息
       // String tName = (String) session.getAttribute("tName");
      //  exam.settName(tName);
       boolean flag =  examService.saveExaminationInfo(exam);
       List<Map> mapList  = commonController.getExamineInfo();
       if(flag){
           resultMap.put("status","200");
           resultMap.put("message","添加考试成功");
           resultMap.put("examlists",mapList);
       }else {
           resultMap.put("status","500");
           resultMap.put("message","添加考试失败");
       }
       return resultMap;
    }

    /**
     * 教师上传考试试卷
     *
     * @param examName
     * @param multipartFile
     * @param session
     */
    @RequestMapping("/uploadExamPaper")
    @ResponseBody
    public void uploadExamPaper(String examName,MultipartFile multipartFile,HttpSession session){
        session.setAttribute("examName",examName);
        try {
            paperService.SavePaperService(multipartFile,session);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止考试
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
     * 教师更新考试的接口
     *
     * @param exam
     * @return
     */
    @RequestMapping("/updateExamInfo")
    public Map<String,Object> updateExamInfo(TExam exam){
        boolean flag = examService.updateExamInfo(exam);
        if(!flag){
            resultMap.put("status",500);
        }
        resultMap.put("status",200);
        return resultMap;
    }

   /* @RequestMapping("/selectAllExamInfo")
    @ResponseBody
    public List<TExam> selectAllExamInfo(){

        return examService.selectAllExamInfo();
    }*/
   @RequestMapping("/selectExamInfoByTName")
   @ResponseBody
   public TExam selectExamInfoByTName(){

       String tName = "xwc";
       return examService.selectExamInfoByTExam(tName);
   }
}

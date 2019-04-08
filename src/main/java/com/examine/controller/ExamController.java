package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TExam;
import com.examine.domain.TSystem;
import com.examine.service.ExamService;
import com.examine.service.SavePaperService;
import com.examine.service.SystemService;
import com.examine.service.TeacherService;
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

    private final SavePaperService savePaperService;

    private final SystemService systemService;

    @Autowired
    public ExamController(TeacherService teacherService, ExamService examService, SavePaperService savePaperService,SystemService systemService) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.savePaperService = savePaperService;
        this.systemService = systemService;
    }

    /*
     * 考试清理界面初始化
     */
    @RequestMapping(value="/admin_exam")
    public ModelAndView admin_exam() throws ParseException {
        ModelAndView mv = new ModelAndView();
        TSystem tSystem = systemService.selectSystemConfigure();
        List<TExam> tExams = examService.selectAllExamInfo();
        List<Map> listmap  = new ArrayList<>();
        String startTime;
        String examTime;
        for (TExam tExam: tExams) {
            startTime= tExam.getExamStartTime();
            examTime = tSystem.getsExamTime();
            boolean status = isTestFinished(startTime,examTime);
            if(tExam.getIsAutoStart()==1){
                resultMap.put("isautostart","是");
            }else {
                resultMap.put("isautostart","否");
            }
            if(tExam.getIsStart()==1){
                if(status){
                    resultMap.put("isexam","否");
                    resultMap.put("isfinished","是");
                }else{
                    resultMap.put("isexam","是");
                    resultMap.put("isfinished","否");
                }
            }else {
                resultMap.put("isexam","未开始");
                resultMap.put("isfinished","未开始");
            }
            if(tExam.getIsPigeonhole()==1){
                resultMap.put("ispageonhole","是");
            }else{
                resultMap.put("ispageonhole","否");
            }
            if (tExam.getIsDelete()==1){
                resultMap.put("isdelete","是");
            }else {
                resultMap.put("isdelete","否");
            }
            resultMap.put("examname",tExam.getExamName());
            resultMap.put("exam_time",tExam.getExamStartTime());
            resultMap.put("create_name",tExam.gettName());
            resultMap.put("exampaper_url",tExam.getExamPaperUrl());
            listmap.add(resultMap);
        }
        mv.addObject("examlists",listmap);
        mv.setViewName("/admin_exam");
        return mv;
    }
    /**
      * 判断考试状态
      * @parame:
      * @return
     */


    public boolean isTestFinished(String examtime,String ever_time) throws ParseException {
        boolean status = false;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date nowdate = new Date();
        Date examdate = df.parse(examtime);
        long time = nowdate.getTime()-examdate.getTime();
        if(time>(Integer.valueOf(ever_time)*60*1000)){
          status = true;
        }else{
            status = false;
        }
        return status;
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
    public Object saveExam(TExam exam, HttpSession session) {
        //考试信息中包括老师信息
       // String tName = (String) session.getAttribute("tName");
      //  exam.settName(tName);
       boolean flag =  examService.saveExaminationInfo(exam);
       if(flag){
           resultMap.put("status","200");
           resultMap.put("message","添加考试成功");
       }else {
           resultMap.put("status","500");
           resultMap.put("message","添加考试失败");
       }
       return resultMap;
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

   /* @RequestMapping("/selectAllExamInfo")
    @ResponseBody
    public List<TExam> selectAllExamInfo(){

        return examService.selectAllExamInfo();
    }*/
}

package com.examine.common.controller;

import com.examine.domain.TExam;
import com.examine.domain.TSystem;
import com.examine.domain.TTeacher;
import com.examine.service.ExamService;
import com.examine.service.SystemService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CommonController extends BaseController {

    private final TeacherService teacherService;

    private final SystemService systemService;

    private final ExamService examService;

    @Autowired
    public CommonController(TeacherService teacherService, ExamService examService, SystemService systemService) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.systemService = systemService;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/editPassword")
    @ResponseBody
    public Map<String, Object> editPassword(TTeacher tTeacher) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("tName", tTeacher.gettName());
        parameterMap.put("tPass", tTeacher.gettPass());
        if (teacherService.updateAccountByUsername(parameterMap) == 1) {
            resultMap.put("status", 200);
            resultMap.put("message", "update success");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "update error");
        }

        return resultMap;
    }

    @RequestMapping("/checkPasswordByname")
    @ResponseBody
    public Map<String, Object> checkPasswordByname(@RequestParam(value = "tName") String name) {
        Map<String, Object> parameterMap = new HashMap<>();
        String pd = teacherService.selectTeacherPasswordByUsername(name);
        if (pd != null) {
            resultMap.put("status", 200);
            resultMap.put("content", pd);
        } else {
            resultMap.put("status", 500);
        }
        return resultMap;
    }

    /**
     * 管理员根据type属性哦获取所有的考试信息和单个教师相关的考试信息
     *
     * @return
     * @parame:
     */
    public List<Map> getExamineInfo(String type, Integer examTime) throws ParseException {
        TSystem tSystem = systemService.selectSystemConfigure();
        List<TExam> tExams = null; //获取所有的考试信息
        List<Map> listmap = new ArrayList<>();
        /*
         * 根据type参数来区分管理员请求和单个教师请求
         * */
        if (type.equals("") && examTime == -1) {
            tExams = examService.selectAllExamsInfo();
        } else {
            //考后
            if (examTime == 0) {
                tExams = examService.selectAfterExamInfo(type);
            }
            //考前
            if (examTime == 1) {
                tExams = examService.selectBeforeExamInfo(type);
            }

            if(examTime == 2){
                tExams = examService.selectExamInfoByTName(type);
            }
        }
        if (tExams != null) {
          /*  String startTime;
            String examTime;*/
            for (TExam tExam : tExams) {
                Map<Object, Object> resultMap = new HashMap<>();

                resultMap.put("examname", tExam.getExamName());
                resultMap.put("exam_time", tExam.getExamStartTime());
                resultMap.put("create_name", tExam.gettName());
                resultMap.put("examId", tExam.getId());
                resultMap.put("isautostart", tExam.getIsAutoStart());
                resultMap.put("isexam", tExam.getIsStart());
                resultMap.put("isfinished", tExam.getIsFinished());
                resultMap.put("ispageonhole", tExam.getIsPigeonhole());
                resultMap.put("isdelete", tExam.getIsDelete());
                resultMap.put("exampaper_url", tExam.getExamPaperUrl());

                listmap.add(resultMap);
            }
        }

        return listmap;
    }

    /**
     * 判断考试状态
     *
     * @return
     * @parame:
     */
    public boolean isTestFinished(String examtime, String ever_time) throws ParseException {
        boolean status = true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowdate = new Date();
        Date examdate = df.parse(examtime);
        long time = nowdate.getTime() - examdate.getTime();
        //判断当前时间与考试开始时间相差是否大于该场考试所需时间
        if (time > (Integer.valueOf(ever_time) * 60 * 1000)) {
            status = false;
        }
        return status;
    }

    /**
     * 判断考试自动开始时当前时间是否与考试时间一致
     *
     * @return
     * @parame:
     */
    public boolean is_timeStart(String examtime) throws ParseException {
        boolean status = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowdate = new Date();
        Date examdate = df.parse(examtime);
        /*判断当前时间以到考试开始时间*/
        if (nowdate.getTime() >= examdate.getTime()) {
            status = true;
        }
        return status;
    }

}

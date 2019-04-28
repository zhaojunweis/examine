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

import javax.servlet.http.HttpServletResponse;
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
    public CommonController(TeacherService teacherService,ExamService examService,SystemService systemService) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.systemService = systemService;
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
        if(pd!=null){
            resultMap.put("status",200);
            resultMap.put("content",pd);
        }else {
            resultMap.put("status",500);
        }
        return resultMap;
    }
    /**
      * 根据type属性哦获取所有的考试信息和单个教师相关的考试信息
      * @parame:
      * @return
     */



    public List<Map> getExamineInfo(String type) throws ParseException {
        TSystem tSystem = systemService.selectSystemConfigure();
        List<TExam> tExams = null; //获取所有的考试信息
        List<Map> listmap  = new ArrayList<>();
        /*
        * 根据type参数来区分管理员请求和单个教师请求
        * */
        if(type.equals("")){
            tExams = examService.selectAllExamInfo();
        }else {

            tExams = examService.selectExamInfoByTName(type);

        }
        if(tExams!=null){
            String startTime;
            String examTime;
            for (TExam tExam: tExams) {
                Map<Object,Object> resultMap = new HashMap<>();
                /*
                * 当为自动开启考试时，starttime为创建考试时的考试时间
                * 当为非自动开启考试时，starttime为教师手动开始的时间
                * */

                examTime = tSystem.getsExamTime();
                boolean status = false;

                /*
                * 是否自动开始，如果是自动开始则按考试开始时间来算
                * */
                if(tExam.getIsAutoStart()==1){
                    resultMap.put("isautostart","1");
                    /*
                    * 如果是自动开始，应该先判断是否已到考试时间，如果到了,则修改标志位is_start,并且在判断是考试中还是考试后
                    * */
                    startTime= tExam.getExamStartTime();
                    /*
                    * 判断starttime是否到了当前时间，如果到了就修改该次考试的标志位is_start
                    * */
                    if(is_timeStart(startTime)){
                        if(tExam.getIsStart()!=1){
                            //考试还未开始，修改is_start标志位
                        }
                    }
                    try {
                        status = isTestFinished(startTime,examTime);
                        if(status){
                            resultMap.put("isexam","1");
                            resultMap.put("isfinished","0");
                        }else{
                            resultMap.put("isexam","0");
                            resultMap.put("isfinished","1");
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }else {
                    resultMap.put("isautostart","0");
                    /*此部分的考试开始时间start_time应该为教师手动开启的时间*/
                    startTime= tExam.getExamStartTime();
                    status = isTestFinished(startTime,examTime);
                    if(tExam.getIsStart()==1){
                        if(status){
                            resultMap.put("isexam","1");
                            resultMap.put("isfinished","0");
                        }else{
                            resultMap.put("isexam","0");
                            resultMap.put("isfinished","1");
                        }
                    }else {
                        resultMap.put("isexam","0");
                        resultMap.put("isfinished","0");
                    }
                }

                if(tExam.getIsPigeonhole()==1){
                    resultMap.put("ispageonhole","1");
                }else{
                    resultMap.put("ispageonhole","0");
                }
                if (tExam.getIsDelete()==1){
                    resultMap.put("isdelete","1");
                }else {
                    resultMap.put("isdelete","0");
                }
                resultMap.put("examname",tExam.getExamName());
                resultMap.put("exam_time",tExam.getExamStartTime());
                resultMap.put("create_name",tExam.gettName());
                resultMap.put("examId",tExam.getId());
                if(tExam.getExamPaperUrl()!=null){
                    resultMap.put("exampaper_url","已上传");
                }else{
                    resultMap.put("exampaper_url","未上传");
                }
                listmap.add(resultMap);
            }
        }

        return listmap;
    }
    /**
     * 判断考试状态
     * @parame:
     * @return
     */


    public boolean isTestFinished(String examtime,String ever_time) throws ParseException {
        boolean status = true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowdate = new Date();
        Date examdate = df.parse(examtime);
        long time = nowdate.getTime()-examdate.getTime();
        /*

        * 判断当前时间与考试开始时间相差是否大于该场考试所需时间*/
        if(time>(Integer.valueOf(ever_time)*60*1000)){
            status = false;
        }
        return status;
    }
    /**
      * 判断考试自动开始时当前时间是否与考试时间一致
      * @parame:
      * @return
     */
    public boolean is_timeStart(String examtime) throws ParseException {
        boolean status = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowdate = new Date();
        Date examdate = df.parse(examtime);
        /*判断当前时间以到考试开始时间*/
        if(nowdate.getTime()>=examdate.getTime()){
            status = true;
        }
        return status;
    }

}

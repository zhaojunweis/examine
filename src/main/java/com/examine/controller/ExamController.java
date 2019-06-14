package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.controller.CommonController;
import com.examine.common.util.LimitPage;
import com.examine.domain.TExam;
import com.examine.domain.TSystem;
import com.examine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public DaemonService daemonService;

    @Autowired
    public ExamController(TeacherService teacherService, ExamService examService, PaperService paperService, SystemService systemService, CommonController commonController) {
        this.teacherService = teacherService;
        this.examService = examService;
        this.paperService = paperService;
        this.systemService = systemService;
        this.commonController = commonController;
    }

    /**
     * 教师在考前管理界面进入的
     * 考试编辑界面修改考试信息
     *
     * @return
     * @parame:
     */
    @RequestMapping(value = "/examinfo_modifier")
    @ResponseBody
    public Map<String, Object> examinfo_modifier(TExam tExame) {
        int id = (int) tExame.getId();
        TExam exam = examService.selectOneExamInfoById(id);
        if (exam.getExamName().equals(tExame.getExamName()) && exam.getExamStartTime().equals(tExame.getExamStartTime())) {
            resultMap.put("status", 200);
            resultMap.put("message", "考试信息未变动，无需修改");
        } else {
            boolean tag = examService.updateExamInfo(tExame);
            if (tag) {
                resultMap.put("status", 200);
                resultMap.put("message", "您的考试信息修改成功");
            } else {
                resultMap.put("status", 500);
                resultMap.put("massage", "考试信息修改失败，请重试");
            }
        }

        return resultMap;

    }


    /**
     * 根据考试名称清理该场考试
     *
     * @return
     * @parame:
     */
    @RequestMapping("/clearExam")
    @ResponseBody
    public Map<String, Object> clearExam(HttpSession session, @RequestParam(value = "Id") Integer id) throws ParseException {
        boolean isSuccess = teacherService.clearExamInfo(id);
        String t_name = (String) session.getAttribute("tName");
        if (isSuccess) {
            resultMap.put("status", 200);
            resultMap.put("message", "清理成功");
            List<Map> examineInfo = null;
            boolean isAdmin = teacherService.isAdmin(t_name);
            if (isAdmin) {
                examineInfo = LimitPage.TransforToMap(examService.selectAllExamsInfo());
            } else {
                examineInfo = commonController.getExamineInfo(t_name, 0);
            }
            resultMap.put("examlists", examineInfo);
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "清理失败");
        }
        return resultMap;
    }

    /**
     * 添加考试
     *
     * @return
     * @parame:
     */
    @RequestMapping("/saveExam")
    public Object saveExam(TExam exam, HttpSession session) throws ParseException {

        //考试信息中包括老师信息
        String tName = (String) session.getAttribute("tName");
        boolean flag = examService.saveExaminationInfo(exam);
        List<TExam> tExamList = null;
        Map<String, Object> map = new HashMap<>();
        if (flag) {
            //添加考试需要扫描Sql
            daemonService.scanMySQL();
            //需要对队列进行扫描
            if(!examService.isExistExam()){
                daemonService.startThread();
                daemonService.changeStatus();
            }
            int count = teacherService.selectCountExamBefore(tName);
            map = LimitPage.limitPage(0, count, 10, 0, 0);
            map.put("tname", tName);
            tExamList = teacherService.selectExamLimitBefore(map);
            resultMap.put("status", "200");
            resultMap.put("message", "添加考试成功");
            resultMap.put("examlists", LimitPage.TransforToMap(tExamList));
            resultMap.put("lastpage", map.get("lastpage"));
        } else {
            resultMap.put("status", "500");
            resultMap.put("message", "添加考试失败");
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
    public void uploadExamPaper(String examName, MultipartFile multipartFile, HttpSession session) {
        session.setAttribute("examName", examName);
        try {
            paperService.SavePaperService(multipartFile, session);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止考试
     *
     * @param id
     * @return
     */
    @RequestMapping("/stopExam")
    @ResponseBody
    public Map<String, Object> stopExam(@RequestParam(value = "Id") Integer id) {
        boolean status = examService.stopOneExamById(id);
        if (!status) {
            resultMap.put("status", 500);
            resultMap.put("message", "停止考试失败");
        } else {
            resultMap.put("status", 200);
            resultMap.put("message", "停止考试成功");
            daemonService.startThread();//设置cancel标志位为true
            daemonService.changeStatus();//扫描并清理考试状态
        }
        return resultMap;
    }

    /**
     * 开始考试
     *
     * @param id
     * @return
     */
    @RequestMapping("/startExam")
    @ResponseBody
    public Map<String, Object> startExam(@RequestParam(value = "Id") Integer id) {

        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        if(examService.isExistExam()){
            resultMap.put("status", 403);
            resultMap.put("message", "已存在开启考试");
        }else {
            boolean status = examService.startExamById(id);
            if (!status) {
                resultMap.put("status", 500);
                resultMap.put("message", "开启考试失败");
            }
            resultMap.put("status", 200);
            resultMap.put("message", "开启考试成功");

        }
        return resultMap;
    }

    /**
     * 教师更新考试的接口
     *
     * @param exam
     * @return
     */
    @RequestMapping("/updateExamInfo")
    public Map<String, Object> updateExamInfo(TExam exam) {
        boolean flag = examService.updateExamInfo(exam);
        if (!flag) {
            resultMap.put("status", 500);
        }
        resultMap.put("status", 200);
        return resultMap;
    }


    /**
     * 考试清理界面初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping(value = "/admin_exam")
    public ModelAndView admin_exam() throws ParseException {
        ModelAndView mv = new ModelAndView();
        //查询出该教师对应有多少考前考试
        int count = teacherService.selectAllExamCount();
        List<TExam> tExamList = null;
        if (count <= 10) {
            resultMap.put("startNum", 0);
            resultMap.put("pageSize", count);
            tExamList = teacherService.selectAllExamLimit(resultMap);
        } else {
            resultMap.put("startNum", 0);
            resultMap.put("pageSize", 10);
            tExamList = teacherService.selectAllExamLimit(resultMap);
        }

        mv.addObject("examlists", LimitPage.TransforToMap(tExamList));
        mv.setViewName("/admin_exam");
        return mv;
    }

    /**
     * 考试清理分页
     *
     * @return
     */
    @RequestMapping("/adminExamLimit")
    @ResponseBody
    public Map<String, Object> adminExamLimit(@RequestParam(value = "pageSize",
            defaultValue = "10") Integer pageSize, @RequestParam(value = "nowPage", defaultValue = "1")
                                                      Integer nowPage, @RequestParam(value = "type", defaultValue = "2") int type) {

        List<TExam> examList = null;
        Map<String, Object> map = new HashMap<>();
        int count = teacherService.selectAllExamCount();
        map = LimitPage.limitPage(0, count, pageSize, nowPage, type);
        if (map == null) {
            return null;
        }
        examList = teacherService.selectAllExamLimit(map);
        resultMap.put("examlists", LimitPage.TransforToMap(examList));
        resultMap.put("lastpage", map.get("lastpage"));
        return resultMap;
    }
}

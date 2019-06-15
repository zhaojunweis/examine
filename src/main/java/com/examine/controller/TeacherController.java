package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.controller.CommonController;
import com.examine.common.util.ExcelUtils;
import com.examine.common.util.LimitPage;
import com.examine.common.util.StringUtils;
import com.examine.common.util.ZipUtils;
import com.examine.domain.TExam;
import com.examine.domain.TNotification;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

@Controller
public class TeacherController extends BaseController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final SubmitService submitService;

    private final SystemService systemService;

    private final ExamService examService;

    private final CommonController commonController;

    private NotificationService notificationService;

    @Autowired
    private DaemonService daemonService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService, SubmitService submitService, ExamService examService, SystemService systemService,
                             NotificationService notificationService, CommonController commonController) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.submitService = submitService;
        this.examService = examService;
        this.systemService = systemService;
        this.commonController = commonController;
        this.notificationService = notificationService;
    }





    /**
     * 考试概况初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_manage_summary")
    public ModelAndView manage_summary(HttpSession session) throws ParseException {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        String tname = (String) session.getAttribute("tName");
        List<Map> exams = commonController.getExamineInfo(tname,2);
        String sScoreName = null;
        for (Map exam : exams) {
            /*当通过后台代码查询isexam=1,isfinished=0时有考试正在进行,返回该场考试的名称，并且跳出循环*/
            if ((int) exam.get("isexam") == 1 && (int) exam.get("isfinished") == 0) {

                sScoreName = (String) exam.get("examname");
                break;
            }
        }
        if (sScoreName == null) {
            mv.setViewName("/teacher_manage_summary");
        } else {
            Map<String, Integer> examinfo = studentService.studentCountOneExam(sScoreName);
            mv.addObject("examinfo", examinfo);
            mv.setViewName("/teacher_manage_summary_inexam");
        }

        return mv;
    }

    /**
     * 学生信息初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_manage_student")
    public ModelAndView manage_student(HttpSession session) throws ParseException {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        String tname = (String) session.getAttribute("tName");
        List<Map> exams = commonController.getExamineInfo(tname,2);
        String sScoreName = null;
        for (Map exam : exams) {
            /*当通过后台代码查询isexam=1,isfinished=0时有考试正在进行,返回该场考试的名称，并且跳出循环*/
            if ((int) exam.get("isexam") == 1 && (int) exam.get("isfinished") == 0) {

                sScoreName = (String) exam.get("examname");
                break;
            }
        }
        if (sScoreName == null) {
            mv.setViewName("/teacher_manage_student");
        } else {
          /*  Map<String, Integer> examinfo = studentService.studentCountOneExam(sScoreName);
            mv.addObject("examinfo", examinfo);*/
            mv.setViewName("/teacher_manage_student_inexam");
        }

        return mv;
    }

    /**
     * 解锁绑定初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_manage_unlock")
    public ModelAndView manage_unlock(HttpSession session) throws ParseException {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        String tname = (String) session.getAttribute("tName");
        List<Map> exams = commonController.getExamineInfo(tname,2);
        String sScoreName = null;
        for (Map exam : exams) {
            /*当通过后台代码查询isexam=1,isfinished=0时有考试正在进行,返回该场考试的名称，并且跳出循环*/
            if ((int) exam.get("isexam") == 1 && (int) exam.get("isfinished") == 0) {

                sScoreName = (String) exam.get("examname");
                break;
            }
        }
        if (sScoreName == null) {
            mv.setViewName("/teacher_manage_unlock");
        } else {
           /* Map<String, Integer> examinfo = studentService.studentCountOneExam(sScoreName);
            mv.addObject("examinfo", examinfo);*/
            mv.setViewName("/teacher_manage_unlock_inexam");
        }
        return mv;
    }

    /**
     * 通知管理初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_manage_notify")
    public ModelAndView manage_notify(HttpSession session) throws ParseException {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        String tname = (String) session.getAttribute("tName");
        List<Map> exams = commonController.getExamineInfo(tname,2);
        String sScoreName = null;
        for (Map exam : exams) {
            /*当通过后台代码查询isexam=1,isfinished=0时有考试正在进行,返回该场考试的名称，并且跳出循环*/
            if ((int) exam.get("isexam") == 1 && (int) exam.get("isfinished") == 0) {

                sScoreName = (String) exam.get("examname");
                break;
            }
        }

        if (sScoreName == null) {
            mv.setViewName("/teacher_manage_notify");
        } else {
        /*    Map<String, Integer> examinfo = studentService.studentCountOneExam(sScoreName);
            mv.addObject("examinfo", examinfo);*/
            //根据scorename查询examId
            int examId = teacherService.selectExamIdByExamName(sScoreName);
            mv.addObject("examId",examId);
            mv.setViewName("/teacher_manage_notify_inexam");
            mv.addObject("notifies", notificationService.showAllNotification(examId));
        }
        return mv;
    }

    /**
     * 考前编辑
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_exam_modify")
    public ModelAndView exam_modify(@Param(value = "Id") int Id, HttpSession session) {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        TExam exam = examService.selectOneExamInfoById(Id);
        session.setAttribute("examName", exam.getExamName());
        mv.addObject("examinfo", exam);
        mv.setViewName("/teacher_exam_modify");
        return mv;
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

        String username = tTeacher.gettName();
        String password = "";
        //如果查询数据库中tname对应的tpass与前台传来的一致,则进行权限校验，否则程序结束立即返回信息给前台
        if (!teacherService.selectTeacherPasswordByUsername(tTeacher.gettName()).equals(tTeacher.gettPass())) {
            resultMap.put("status", "500");
            resultMap.put("message", "您输入的密码错误，请确认后再登陆");
            return resultMap;
        } else {
            password = tTeacher.gettPass();
        }

        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            session.setAttribute("tName", tTeacher.gettName());
            resultMap.put("status", "200");
            resultMap.put("url", "/teacher_main");
            resultMap.put("message", "登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap.put("status", "403");
            resultMap.put("message", "您没有教师权限,登录失败");
        }
        return resultMap;
    }

    /**
     * 教师首页初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_main")
    public ModelAndView teacher_main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher_main");
        return mv;
    }

    /**
     * 导入学生信息到数据库中
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping("/importStudentInfo")
    @ResponseBody
    public Map<String, Object> importStudentInfo(@RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile,@RequestParam("examId") Integer examId) {

        /*首先上传excel文件,并返回excel的文件位置
         * */
        String uploadexcel = ExcelUtils.uploadExcelFile(multipartFile);
        if (uploadexcel.equals("")) {
            resultMap.put("status", "500");
            resultMap.put("message", "上传excel错误，添加失败");
        } else {
            boolean importStatus = studentService.importStudentInfo(uploadexcel,examId);
            if (!importStatus) {
                resultMap.put("status", "500");
                resultMap.put("message", "学生名单导入失败");
            }
            resultMap.put("status", "200");
            resultMap.put("message", "学生名单导入成功");
        }
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
    @RequestMapping(value = "/downLoadZipFile",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void downLoadZipFile(HttpServletResponse response){
        String zipName = UUID.randomUUID().toString() + ".zip";
        List<String> fileList = submitService.downloadSubmitZip();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment;filename=" + zipName);
        try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            for (String aFileList : fileList) {
                if (aFileList != null) {
                    //文件的上一级目录
                    String parentDirDown = new File(aFileList).getParent();
                    ZipUtils.doCompress(parentDirDown, out);
                    response.flushBuffer();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/doPageOnhole")
    @ResponseBody
    public Map<String, Object> doPageOnhole(HttpSession session,@RequestParam(value = "Id") Integer id) throws ParseException {
        //修改exam的标志位pageonwhole
        boolean b = submitService.finishedPageOnHole(id);
        String t_name = (String) session.getAttribute("tName");
        if(b){
            resultMap.put("status",200);
            resultMap.put("examlists", commonController.getExamineInfo(t_name,0));
            resultMap.put("onloadUrl","/downLoadZipFile");
        }else {
            resultMap.put("status",500);
            resultMap.put("message","归档失败，请重试");
        }
        return resultMap;
    }

    /**
     * 教师手动添加学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/saveStudent")
    @ResponseBody
    public Map<String, Object> saveStudent(TStudent student,@RequestParam("examId") Integer examId) {
        student.setScoreId(examId);
        boolean flag = studentService.insertStudent(student);
        if (!flag) {
            resultMap.put("status", 500);
            resultMap.put("message", "学生添加失败");
        }
        resultMap.put("status", 200);
        resultMap.put("message", "学生添加成功");
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
    public Map<String, Object> updateDirtyData(TStudent student) {
        boolean flag = studentService.updateDirtyData(student);
        if (!flag) {
            resultMap.put("status", 500);
            resultMap.put("message", "update failed");
        }
        resultMap.put("status", 200);
        resultMap.put("message", "update success");
        return resultMap;
    }

    /**
     * 学生信息解绑与IP
     *
     * @param sSno
     * @return
     */
    @RequestMapping("/doUnbinding")
    @ResponseBody
    public Map<String, Object> doUnbinding(@RequestParam(value = "sSno")String sSno) {
        Integer integer = submitService.doUnbinding(sSno);
        if (integer != 1) {
            resultMap.put("status", 500);
            resultMap.put("message","解绑失败");
            return resultMap;
        }
        resultMap.put("status", 200);
        resultMap.put("message","解绑成功");
        return resultMap;
    }

    /**
     * 查看进行中的考试信息学生的提交信息，登陆信息，考试学生总数等
     *
     * @return
     */
    @RequestMapping("/viewInfoOnTestInProgress")
    @ResponseBody
    public Map<String, Integer> viewInfoOnTestInProgress() {

        return studentService.studentCountOneExam("Java");
    }

    /**
     * 教师根据学号查找学生相关信息
     *
     * @param sno
     * @return
     */
    @RequestMapping(value = "/queryStudentEntity")
    @ResponseBody
    public Map<String, Object> queryStudentEntity(@RequestParam(value = "sno") String sno) {

        TStudent student = studentService.selectStudentEntityByUsername(sno);
        if (student != null) {
            resultMap.put("studentinfo", student);
            resultMap.put("status", 200);
            return resultMap;
        }
        resultMap.put("status", 500);
        resultMap.put("message", "未找到该学生的相关信息");
        return resultMap;
    }

    /**
     * 添加通知信息
     * @param tNotification
     * @return
     */
    @RequestMapping("/addNotifycation")
    @ResponseBody
    public Map<String, Object> addNotifycation(TNotification tNotification) {

        boolean b = notificationService.saveNotification(tNotification);
        if (b) {
            resultMap.put("status", 200);
            resultMap.put("notifies", notificationService.showAllNotification((int)tNotification.getId()));
            resultMap.put("message", "添加通知成功");
            return resultMap;
        }
        resultMap.put("status", 500);
        resultMap.put("message", "添加通知失败");
        return resultMap;
    }

    /**
     * 根据Ip查找学生信息
     * @param Ip
     * @return
     */
    @RequestMapping("/selectStudentByIp")
    @ResponseBody
    public Map<String,Object> selectStudentByIp( @RequestParam(value = "IpAddress")String Ip){
        TStudent tStudent = studentService.selectStudentByIp(Ip);
        if(tStudent != null){
            resultMap.put("status",200);
            resultMap.put("studentinfo", tStudent);
            return resultMap;
        }
        resultMap.put("status", 500);
        resultMap.put("message", "未找到该学生的相关信息");
        return resultMap;
    }
    /**
     * 根据前台传来的数据进行分页，获取后台数据
     * @param id 该场考试的
     * @param pageSize 传来的每页的大小
     * @param nowPage 第几页
     * @param type 判断是第一页，最后一页，还是正常分页 type = 1:首页，type=0:最后一页,type=2:正常分页
     * @return
     */
    @RequestMapping("/getLimitPage")
    @ResponseBody
    public Map<String,Object> getStudentLimitPage(@RequestParam(value = "Id") Integer id,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "nowPage" ,defaultValue = "1") Integer nowPage,@RequestParam(value = "type",defaultValue = "2") int type)
    {
        List<TStudent> studentlist = null;
        Map<String,Object> map = new HashMap<>();
        int count = teacherService.selectCountByExamId(id);
        map = LimitPage.limitPage(id,count,pageSize,nowPage,type);
        if(map == null){
            return null;
        }
        studentlist = teacherService.selectByLimit(map);
        resultMap.put("studentlist",studentlist);
        resultMap.put("lastpage",map.get("lastpage"));
        return resultMap;
    }
    /**
     * 考前管理界面的考试编辑中添加学生名单
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_addstudent")
    public ModelAndView showAddStudent(@RequestParam(value = "Id") int id) {

        ModelAndView mv = new ModelAndView();
        //读取第一页的数据limit 0,10 默认查询如果当前的数据总数count<= 10 则查询limit 0,count，如果count>10查询limit 0,10
        int count = teacherService.selectCountByExamId(id);
        List<TStudent> studentlist = null;

        resultMap.put("examId",id);
        if(count<=10){
            resultMap.put("startNum",0);
            resultMap.put("pageSize",count);
            studentlist = teacherService.selectByLimit(resultMap);
        }else {
            resultMap.put("startNum",0);
            resultMap.put("pageSize",10);
            studentlist = teacherService.selectByLimit(resultMap);
        }
        mv.addObject("Id", id);
        mv.addObject("studentlist",studentlist);
        mv.setViewName("/teacher_addstudent");
        return mv;
    }
    /**
     * 考前操作初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_exam_before")
    public ModelAndView exam_Befor(@RequestParam(defaultValue = "admin", value = "t_name") String t_name) throws ParseException {
        daemonService.startThread();//设置cancel标志位为true
        daemonService.changeStatus();//扫描并清理考试状态

        ModelAndView mv = new ModelAndView();
        //查询出该教师对应有多少考前考试
        int count = teacherService.selectCountExamBefore(t_name);
        List<TExam> tExamList = null;
        resultMap.put("tname",t_name);
        if(count<=10){
            resultMap.put("startNum",0);
            resultMap.put("pageSize",count);
            tExamList = teacherService.selectExamLimitBefore(resultMap);
        }else {
            resultMap.put("startNum",0);
            resultMap.put("pageSize",10);
            tExamList = teacherService.selectExamLimitBefore(resultMap);
        }

        mv.addObject("examlists", LimitPage.TransforToMap(tExamList));
        mv.setViewName("/teacher_exam_before");
        return mv;
    }
    /**
     * 根据前台传来的数据进行分页，获取后台数据
     * @param pageSize 传来的每页的大小
     * @param nowPage 第几页
     * @param type 判断是第一页，最后一页，还是正常分页 type = 1:首页，type=0:最后一页,type=2:正常分页
     * @return
     */
    @RequestMapping("/getExamLimitPageBefore")
    @ResponseBody
    public Map<String,Object> getExamLimitPageBefore(HttpSession session,@RequestParam(value = "pageSize",
                                                             defaultValue = "10") Integer pageSize, @RequestParam(value = "nowPage" ,defaultValue = "1")
                                                             Integer nowPage,@RequestParam(value = "type",defaultValue = "2") int type)
    {
        String tName = (String) session.getAttribute("tName");
        List<TExam> examList = null;
        Map<String,Object> map = new HashMap<>();

        int count = teacherService.selectCountExamBefore(tName);
        map = LimitPage.limitPage(0,count,pageSize,nowPage,type);
        if(map == null){
            return null;
        }
        //flag=1為考前操作，=0為靠後操作
        map.put("tname",tName);
        examList = teacherService.selectExamLimitBefore(map);
        resultMap.put("examlists",LimitPage.TransforToMap(examList));
        resultMap.put("lastpage",map.get("lastpage"));
        return resultMap;
    }


    /**
     * 靠后操作初始化
     *
     * @return
     * @parame:
     */
    @RequestMapping("/teacher_exam_after")
    public ModelAndView exam_after(@RequestParam(defaultValue = "admin", value = "t_name") String t_name) throws ParseException {

        ModelAndView mv = new ModelAndView();
        //查询出该教师对应有多少考前考试
        int count = teacherService.selectCountExamAfter(t_name);
        List<TExam> tExamList = null;
        resultMap.put("tname",t_name);
        if(count<=10){
            resultMap.put("startNum",0);
            resultMap.put("pageSize",count);
            tExamList = teacherService.selectExamLimitAfter(resultMap);
        }else {
            resultMap.put("startNum",0);
            resultMap.put("pageSize",10);
            tExamList = teacherService.selectExamLimitAfter(resultMap);
        }

        mv.addObject("examlists", LimitPage.TransforToMap(tExamList));
        mv.setViewName("/teacher_exam_after");
        return mv;
    }

    @RequestMapping("/getExamLimitPageAfter")
    @ResponseBody
    public Map<String,Object> getExamLimitPageAfter(HttpSession session,@RequestParam(value = "pageSize",
            defaultValue = "10") Integer pageSize, @RequestParam(value = "nowPage" ,defaultValue = "1")
                                                       Integer nowPage,@RequestParam(value = "type",defaultValue = "2") int type)
    {
        String tName = (String) session.getAttribute("tName");
        List<TExam> examList = null;
        Map<String,Object> map = new HashMap<>();

        int count = teacherService.selectCountExamAfter(tName);
        map = LimitPage.limitPage(0,count,pageSize,nowPage,type);
        if(map == null){
            return null;
        }
        //flag=1為考前操作，=0為靠後操作
        map.put("tname",tName);
        examList = teacherService.selectExamLimitAfter(map);
        resultMap.put("lastpage",map.get("lastpage"));
        resultMap.put("examlists",LimitPage.TransforToMap(examList));
        return resultMap;
    }

    /**
     * 查询所有消息
     *
     * @return
     */
    @RequestMapping("/selectMessage")
    @ResponseBody
    public Map<String,Object> getAllNotification(@RequestParam(value = "examId") Integer examId) {

        List<TNotification> tNotificationList = notificationService.showAllNotification(examId);
        if(tNotificationList == null){
            resultMap.put("notifies",tNotificationList);
            resultMap.put("message","当前没有考试通知");
        }else{
            resultMap.put("notifies",tNotificationList);
        }
        return resultMap;
    }
}

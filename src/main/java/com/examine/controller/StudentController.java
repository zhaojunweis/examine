package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.IpUtil;
import com.examine.common.util.LimitPage;
import com.examine.common.util.StringUtils;
import com.examine.domain.TExam;
import com.examine.domain.TNotification;
import com.examine.domain.TStudent;
import com.examine.domain.TSubmit;
import com.examine.service.*;
import org.apache.fop.fonts.truetype.TTFSubSetFile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.docx4j.wml.Id;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class StudentController extends BaseController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StudentController.class);   
    private final StudentService studentService;

    private final SubmitService submitService;

    private final ExamService examService;

    private final TeacherService teacherService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    public StudentController(SubmitService submitService, StudentService studentService, ExamService examService,TeacherService teacherService) {
        this.submitService = submitService;
        this.studentService = studentService;
        this.examService = examService;
        this.teacherService = teacherService;
    }


    /**
     * 学生首页初始化
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/student_main")
    public ModelAndView stu_Success(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        TStudent tStudent = (TStudent) session.getAttribute("student");
        session.setAttribute("sno", tStudent.getsSno());
        session.setAttribute("examId", tStudent.getScoreId());
        TExam tExam = examService.selectOneExamInfoById(tStudent.getScoreId());
        if (tExam.getIsStart() == 1 && tExam.getIsFinished() == 0) { //判断该学生的考试是否已经开启了
            mv.addObject("examinfo", tExam);
            mv.setViewName("/student_main");
        } else {
            mv.setViewName("/success");
        }
        return mv;
    }

    /**
     * 查看提交初始化sFileSize
     *
     * @return
     */
    @RequestMapping("/student_exam_listdir")
    public ModelAndView student_exam_listdir(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String sno = (String)session.getAttribute("sno");
        Integer examId = (Integer) session.getAttribute("examId");
        List<TSubmit> listsubmits = null;
        List<Map> maplist = new ArrayList<Map>();
        Map map = new HashMap();
        map.put("sno",sno);
        map.put("examId",examId);
        listsubmits = submitService.selectSubmitResult(map);
        if(listsubmits!=null){
            for (TSubmit submit : listsubmits){
                Map map1 = new HashMap();
                //时间置换
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map1.put("submitTime",sd.format(submit.getCreateTime()));
                //获取filesize
                map1.put("submitFileSize",submit.getsFileSize()+"KB");
                //获取文件名
                String filepath = submit.getExamPaper();
                String filename = "";
                if(filepath != null){
                    filename  = filepath.substring(filepath.lastIndexOf("\\")+1,filepath.length());
                }else {
                    maplist = null;
                    break;
                }

                map1.put("fileName",filename);
                maplist.add(map1);
            }
        }
        mv.addObject("maplist",maplist);
        mv.setViewName("/student_exam_listdir");
        return mv;
    }

    /**
     * 学生登录验证
     *
     * @return
     * @parame:
     */
    @RequestMapping(value = "/submitStudentLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(TStudent student, HttpSession session, HttpServletRequest request) {
        String sno = student.getsSno();
        String sname = "";
        TStudent tStudent;
        tStudent = studentService.selectStudentEntityByUsername(sno);
        logger.info(student.getsSno() + " " + student.getsName());
        String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
        String ip = IpUtil.getLocalIp1(request);
        if (tStudent != null) {
            if (!student.getsName().equals(tStudent.getsName())) {
                resultMap.put("status", 500);
                resultMap.put("message", "账号密码错误，登录失败");
                return resultMap;
            } else if (StringUtils.isBlank(loginIp) || loginIp.equals(ip)) {
                //如果登陆Ip为空，则插入，否者不变
                if (StringUtils.isBlank(loginIp)) {
                    Map map = new HashMap();
                    map.put("sno",sno);
                    map.put("examId",tStudent.getScoreId());
                    if(submitService.selectSubmitEntity(map)==null){
                        submitService.insertStudentLoginMessage(sno, ip,tStudent.getScoreId());
                    }else {
                        map.put("IpAddress",ip);
                        submitService.updateSubmitStudentIP(map);
                    }

                }
                sname = student.getsName();

            } else {
                resultMap.put("status", 404);
                resultMap.put("message", "IP已被绑定，登录失败");
                return resultMap;
            }
        } else {
            resultMap.put("status", 403);
            resultMap.put("message", "当前学生用户不存在");
            return resultMap;
        }

        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sno, sname);
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                session.setAttribute("student", tStudent);
                resultMap.put("status", "200");
                resultMap.put("url", "/student_main");
                resultMap.put("message", "登录成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap.put("message", "您没有学生权限,登录失败");
            return resultMap;
        }
        return resultMap;
    }


  /*  @RequestMapping("/studentListdir")
    public ModelAndView studentListdir() {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/student_exam_listdir");
        return mv;
    }*/

    /**
     * 学生提交界面初始化
     *
     * @return
     */
    @RequestMapping("/student_exam_upload")
    public ModelAndView student_exam_upload() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/student_exam_listdir");
        return mv;
    }

    /**
     * 学生提交答案
     *
     * @return
     */
    @RequestMapping("/studentsubmit")
    @ResponseBody
    public Map<String, Object> studentsubmit() {
        return null;
    }

    /**
     * 学生下载试卷
     *
     * @return
     */
    @RequestMapping("/studentdoloadpage")
    @ResponseBody
    public Map<String, Object> studentdoloadpage() {
        return null;
    }


    /**
     * 删除学生
     * @param studentId
     * @param id
     * @param pageSize
     * @param nowPage
     * @return
     */
    @RequestMapping("/deleteOneStudent")
    @ResponseBody
    public Map<String,Object> deleteOneStudent(@RequestParam(value = "stuId") Integer studentId,@RequestParam(value = "Id") Integer id,@RequestParam(value = "pageSize",
                                                 defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "nowPage" ,defaultValue = "1") Integer nowPage){

        boolean b = studentService.deleteOneStudent(studentId);
        int count = teacherService.selectCountByExamId(id);
        List<TStudent> studentlist = null;
        Map<String,Object> map = new HashMap<>();
        if(b){
            map = LimitPage.limitPage(id,count,pageSize,nowPage,2);
            if(map != null){
                studentlist = teacherService.selectByLimit(map);
            }
            resultMap.put("status",200);
            resultMap.put("message","删除成功");
            resultMap.put("studentlist",studentlist);
            return resultMap;
        }else {
            resultMap.put("status",500);
            resultMap.put("message","删除失败");
        }
        return  resultMap;
    }

    @RequestMapping("/studentNotifyMessage")
     public ModelAndView studentNotifyMessage(HttpSession session){

        ModelAndView mv = new ModelAndView();
        List<TNotification> tNotificationList = notificationService.showAllNotification
                ((int)session.getAttribute("examId"));
        mv.addObject("notifies",tNotificationList);
        mv.setViewName("/student_notifymessage");
        return mv;
    }

}
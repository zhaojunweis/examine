package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.IpUtil;
import com.examine.common.util.StringUtils;
import com.examine.domain.TExam;
import com.examine.domain.TStudent;
import com.examine.service.ExamService;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import com.examine.service.TeacherService;
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

import javax.servlet.http.HttpSession;
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
    public StudentController(SubmitService submitService, StudentService studentService, ExamService examService,TeacherService teacherService) {
        this.submitService = submitService;
        this.studentService = studentService;
        this.examService = examService;
        this.teacherService = teacherService;
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
     * 查看提交初始化
     *
     * @return
     */
    @RequestMapping("/student_exam_listdir")
    public ModelAndView student_exam_listdir() {
        ModelAndView mv = new ModelAndView();
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
    public Map<String, Object> submitLogin(TStudent student, HttpSession session) {
        String sno = student.getsSno();
        String sname = "";
        TStudent tStudent;
        tStudent = studentService.selectStudentEntityByUsername(sno);
        logger.info(student.getsSno() + " " + student.getsName());
        String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
        String ip = IpUtil.getLocalIp();
        if (tStudent != null) {
            if (!student.getsName().equals(tStudent.getsName())) {
                resultMap.put("status", 500);
                resultMap.put("message", "账号密码错误，登录失败");
                return resultMap;
            } else if (StringUtils.isBlank(loginIp) || loginIp.equals(ip)) {
                //如果登陆Ip为空，则插入，否者不变
                if (StringUtils.isBlank(loginIp)) {
                    submitService.insertStudentLoginMessage(sno, ip);
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

    /**
     * 学生查看提交情况初始化
     *
     * @return
     */
    @RequestMapping("/studentListdir")
    public ModelAndView studentListdir() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/student_exam_listdir");
        return mv;
    }

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
     * 根据前台传来的数据进行分页，获取后台数据
     * @param id 该场考试的
     * @param pageSize 传来的每页的大小
     * @param nowPage 第几页
     * @param type 判断是第一页，最后一页，还是正常分页 type = 1:首页，type=0:最后一页,type=2:正常分页
     * @return
     */
    @RequestMapping("/getLimitPage")
    @ResponseBody
    public Map<String,Object> getLimitPage(@RequestParam(value = "Id") Integer id,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "nowPage" ,defaultValue = "1") Integer nowPage,@RequestParam(value = "type") int type)
    {
        int startNum = 0,pSize = 0;
        List<TStudent> studentlist = null;
        int count = teacherService.selectCountByExamId(id);

        switch (type){
            case 0:
                int page = count/pageSize;
                int abovecount = count%pageSize;
                if(abovecount!=0){
                    startNum = page*pageSize-1;
                    pSize = abovecount;
                }else if (abovecount == 0){
                    startNum = (page-1)*pageSize-1;
                    pSize = pageSize;
                }
                break;
            case 1:
                if (count>pageSize){
                    startNum = 0;
                    pSize = pageSize;
                }else{
                    startNum = 0;
                    pSize = count;
                }
                break;
            default:
                int precount = pageSize*(nowPage-1),lastcount = pageSize*nowPage;
                if((precount<count) && (lastcount<=count)){
                    startNum = precount-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = pageSize;
                }else if(precount<count && lastcount>count){
                    startNum = precount-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = count-precount;
                }else if (precount>=count){
                    return null;
                }
                break;
        }

        resultMap.put("examId",id);
        resultMap.put("startNum",startNum);
        resultMap.put("pageSize",pSize);
        studentlist = teacherService.selectByLimit(resultMap);
        resultMap.put("studentlist",studentlist);
        return resultMap;
    }

}
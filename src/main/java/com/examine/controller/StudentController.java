package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.util.IpUtil;
import com.examine.common.util.StringUtils;
import com.examine.domain.TExam;
import com.examine.domain.TStudent;
import com.examine.service.ExamService;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import org.apache.fop.fonts.truetype.TTFSubSetFile;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class StudentController extends BaseController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    private final SubmitService submitService;

    private final ExamService examService;

    @Autowired
    public StudentController(SubmitService submitService, StudentService studentService,ExamService examService) {
        this.submitService = submitService;
        this.studentService = studentService;
        this.examService = examService;
    }




    /**
      * 考前管理界面的考试编辑中添加学生名单
      * @parame:
      * @return
     */


    @RequestMapping("/teacher_addstudent")
    public ModelAndView showAddStudent(@RequestParam(value = "Id")int id){

        ModelAndView mv = new ModelAndView();
        mv.addObject("Id",id);
        mv.setViewName("/teacher_addstudent");
        return mv;
    }

    /**
     * 学生首页初始化
     * @param session
     * @return
     */
    @RequestMapping(value = "/success")
    public ModelAndView stu_Success(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        TStudent tStudent= (TStudent) session.getAttribute("student");
        TExam tExam = examService.selectOneExamInfoById(tStudent.getScoreId());
        if(tExam.getIsStart()==1 && tExam.getIsFinished()==0){ //判断该学生的考试是否已经开启了
            mv.setViewName("/student_main");
        }else{
            mv.setViewName("/success");
        }
        return mv;
    }


    /**
      * 学生登录验证
      * @parame:
      * @return
     */


    @RequestMapping(value = "/submitStudentLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(TStudent student, HttpSession session) {
        TStudent tStudent;
        tStudent = studentService.selectStudentEntityByUsername(student.getsSno());
        logger.info(student.getsSno() + " " + student.getsName());
        if (tStudent != null) {
            String loginIp = studentService.selectIpAddressByUsername(student.getsSno());
            String ip = IpUtil.getLocalIp();
            if (StringUtils.isBlank(loginIp) || loginIp.equals(ip)) {
                if (student.getsName().equals(tStudent.getsName())) {
                    session.setAttribute("student", tStudent);
                    //如果登陆Ip为空，则插入，否者不变
                    if(StringUtils.isBlank(loginIp)){
                        submitService.insertStudentLoginMessage(tStudent.getsSno(), ip);
                    }
                    resultMap.put("status", 200);
                    resultMap.put("url", "success");
                    resultMap.put("message", "login success");
                } else {
                    resultMap.put("status", 404);
                    resultMap.put("message", "wrong password,Not Found");
                }

            } else {
                resultMap.put("status", 403);
                resultMap.put("message", "you ip address has been used,Forbidden");
            }
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "this user is not exist");
        }

        return resultMap;
    }

    /**
     * 学生查看提交情况初始化
     * @return
     */
    @RequestMapping("/studentListdir")
    public ModelAndView studentListdir(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/student_exam_listdir");
        return  mv;
    }
    /**
     * 学生提交界面初始化
     * @return
     */
    @RequestMapping("/student_exam_upload")
    public ModelAndView student_exam_upload(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/student_exam_listdir");
        return  mv;
    }
    /**
     * 学生提交答案
     * @return
     */
    @RequestMapping("/studentsubmit")
    @ResponseBody
    public Map<String,Object> studentsubmit(){
       return null;
    }
    /**
     * 学生下载试卷
     * @return
     */
    @RequestMapping("/studentdoloadpage")
    @ResponseBody
    public Map<String,Object> studentdoloadpage(){
        return null;
    }


  /*  @RequestMapping(value = "/getStudentLogin")
    @ResponseBody
    public TStudent getStudentLogin(){

        String sSno = "1610120001";
        return studentService.selectStudentRoleAndPerm(sSno);
    }*/

}

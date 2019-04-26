package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.common.controller.CommonController;
import com.examine.common.util.ExcelUtils;
import com.examine.common.util.StringUtils;
import com.examine.common.util.ZipUtils;
import com.examine.domain.TExam;
import com.examine.domain.TStudent;
import com.examine.domain.TSystem;
import com.examine.domain.TTeacher;
import com.examine.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

@Controller
public class TeacherController extends BaseController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final SubmitService submitService;

    private final SystemService systemService;

    private final ExamService examService;

    private final CommonController commonController;
    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService, SubmitService submitService,ExamService examService,SystemService systemService,CommonController commonController) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.submitService = submitService;
        this.examService = examService;
        this.systemService = systemService;
        this.commonController = commonController;
    }


    /**
      *有考试进行时，考中管理的考试概况初始化
      * @parame:
      * @return
     */


    @RequestMapping(value = "/manageNotifyinExam")
    public ModelAndView manageNotifyinExam(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher_manage_notify_inexam");
        return mv;
    }

    /**
      *有考试进行时，考中管理的学生信息初始化
      * @parame:
      * @return
     */


    @RequestMapping(value = "/manageStudentinExam")
    public ModelAndView manageStudentinExam(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher_manage_student_inexam");
        return mv;
    }
    /*
     * 有考试进行时，考中管理的通知管理初始化
     * */
    @RequestMapping(value = "/manageSummaryinExam")
    public ModelAndView manageSummaryinExam(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher_manage_summary_inexam");
        return mv;
    }
    /**
      *有考试进行时，考中管理的解除绑定初始化
      * @parame:
      * @return
     */
    @RequestMapping(value = "/manageUnlockinExam")
    public ModelAndView manageUnlockinExam(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher_manage_unlock_inexam");
        return mv;
    }
/**
  * 考前操作初始化
  * @parame:
  * @return
 */
@RequestMapping("/teacher_exam_before")
public ModelAndView exam_Befor(@RequestParam(defaultValue = "admin", value = "t_name") String t_name) throws ParseException {
    ModelAndView mv = new ModelAndView();
    mv.addObject("examlists",commonController.getExamineInfo(t_name));
    mv.setViewName("/teacher_exam_before");
    return mv;
}
/**
  * 靠后操作初始化
  * @parame:
  * @return
 */
@RequestMapping("/teacher_exam_after")
public ModelAndView exam_after(@RequestParam(defaultValue = "admin", value = "t_name") String t_name) throws ParseException {
    ModelAndView mv = new ModelAndView();
    mv.addObject("examlists",commonController.getExamineInfo(t_name));
    mv.setViewName("/teacher_exam_after");
    return mv;
}
/**
  * 考试概况初始化
  * @parame:
  * @return
 */
@RequestMapping("/teacher_manage_summary")
public ModelAndView manage_summary(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/teacher_manage_summary_inexam");
    return mv;
}
/**
  * 学生信息初始化
  * @parame:
  * @return
 */
@RequestMapping("/teacher_manage_student")
public ModelAndView manage_student() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/teacher_manage_student_inexam");
    return mv;
}
/**
  * 解锁绑定初始化
  * @parame:
  * @return
 */
@RequestMapping("/teacher_manage_unlock")
public ModelAndView manage_unlock() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/teacher_manage_unlock_inexam");
    return mv;
}
/**
  * 通知管理初始化
  * @parame:
  * @return
 */

@RequestMapping("/teacher_manage_notify")
public ModelAndView manage_notify() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/teacher_manage_notify_inexam");
    return mv;
}
/**
  * 考前编辑
  * @parame:
  * @return
 */
@RequestMapping("/teacher_exam_modify")
public ModelAndView exam_modify(@Param(value = "Id")int Id){
    ModelAndView mv = new ModelAndView();
    TExam exam = examService.selectOneExamInfoById(Id);
    mv.addObject("examinfo",exam);
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
        String password = teacherService.selectTeacherPasswordByUsername(tTeacher.gettName());
        if (!StringUtils.isBlank(password, tTeacher.gettPass())) {
            if (password.equals(tTeacher.gettPass())) {
                session.setAttribute("tName", tTeacher.gettName());
                resultMap.put("status", "200");
                resultMap.put("url", "/teacher_main");
                resultMap.put("message", "welcome to teacher");
            } else {
                resultMap.put("message", "wrong password");
            }
        } else {
            resultMap.put("status", 404);
            resultMap.put("message", "not exist!");
        }
        return resultMap;
    }
    /**
      * 教师首页初始化
      * @parame:
      * @return
     */
    @RequestMapping("/teacher_main")
    public ModelAndView teacher_main(){
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
    public Map<String, Object> importStudentInfo(@RequestParam(value = "multipartFile",required = false) MultipartFile multipartFile) {

        /*首先上传excel文件,并返回excel的文件位置
        * */
        String uploadexcel = ExcelUtils.uploadExcelFile(multipartFile);
        if(uploadexcel.equals("")){
            resultMap.put("status","500");
            resultMap.put("message","上传excel错误，添加失败");
        }else {
            boolean importStatus = studentService.importStudentInfo(uploadexcel);
            if (!importStatus) {
                resultMap.put("status", "500");
                resultMap.put("message", "import failed");
            }
            resultMap.put("status", "200");
            resultMap.put("message", "import success");
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
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response) {
        String zipName = UUID.randomUUID().toString() + ".zip";
        List<String> fileList = submitService.downloadSubmitZip();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment;filename=" + zipName);
        try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            for (String aFileList : fileList) {
                if(aFileList != null){
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

    /**
     * 教师手动添加学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/saveStudent")
    @ResponseBody
    public Map<String, Object> saveStudent(TStudent student) {
        boolean flag = studentService.insertStudent(student);
        if (!flag) {
            resultMap.put("status", 500);
            resultMap.put("message", "insert failed");
        }
        resultMap.put("status", 200);
        resultMap.put("message", "insert success");
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
    public Map<String, Object> doUnbinding(String sSno) {
        Integer integer = submitService.doUnbinding(sSno);
        if (integer != 1) {
            resultMap.put("status", 500);
        }
        resultMap.put("status", 200);
        return resultMap;
    }

}

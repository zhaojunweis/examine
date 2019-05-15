package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TSystem;
import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/AdministratorController")
public class AdministratorController extends BaseController {

    private final TeacherService teacherService;

    @Autowired
    public AdministratorController(TeacherService teacherService) {

        this.teacherService = teacherService;
    }

    /**
     * 登录
     *
     * @return
     * @parame:
     */


    @RequestMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("/loginpage");
    }


    /**
     * 教师管理界面初始化
     *
     * @return
     * @parame:
     */


    @RequestMapping(value = "/admin_teacher")
    public ModelAndView admin_teacher() {
        ModelAndView mv = new ModelAndView();
        List<TTeacher> teachers = teacherService.selectAllTeacher();
        mv.addObject("t_list", teachers);
        mv.setViewName("/admin_teacher");
        return mv;
    }

    /**
     * 管理员界面初始化
     *
     * @return
     * @parame:
     */


    @RequestMapping("/admin_main")
    public ModelAndView EnterAdminMain() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin_main");
        return mv;
    }


    /**
     * 管理员登录验证初始化
     *
     * @return
     * @parame:
     */


    @RequestMapping("/submitAdminLogin")
    @ResponseBody
    public Map<String, Object> submitAdminLogin(
            @RequestParam(value = "adminname", defaultValue = "admin") String tName,
            @RequestParam(value = "adminpass", defaultValue = "admin") String tPass,
            HttpSession session) {

        String username = "";
        String password = "";

        if (teacherService.selectCountOtherAdminExceptAdmin()) {
            username = tName;
           if(tPass.equals(teacherService.selectAdminByLoginMessage(tName))){
            password = tPass;
           }else {
               resultMap.put("status", 500);
               resultMap.put("message", "您的密码错误，登录失败");
               return resultMap;
           }

        } else {
            if (("admin".equals(tName)) && ("admin".equals(tPass))) {
                username = "admin";
                password = "admin";
            }
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap.put("status", 403);
            resultMap.put("message", "您没有管理员权限,登录失败");
            return resultMap;
        }

        resultMap.put("status", 200);
        resultMap.put("url", "/admin_main");
        resultMap.put("message", "登录成功");
        session.setAttribute("tName", tName);

        return resultMap;
    }


    /**
     * 添加教师功能
     *
     * @return
     * @parame:
     */

    @RequestMapping("/saveTeacher")
    @ResponseBody
    public Map<String, Object> saveTeacher(TTeacher tTeacher) {

        if (teacherService.selectTeacherPasswordByUsername(tTeacher.gettName()) != null) {
            resultMap.put("status", "500");
            resultMap.put("type", "warning");
            resultMap.put("message", "该用户名已存在,请重新输入");
        } else {
            boolean flag = teacherService.saveTeacher(tTeacher);
            if (flag) {
                List<TTeacher> teachers = teacherService.selectAllTeacher();
                resultMap.put("t_list", teachers);
                resultMap.put("status", "200");
                resultMap.put("message", "添加教师成功!");
            } else {
                resultMap.put("status", "500");
                resultMap.put("type", "erroring");
                resultMap.put("message", "服务器错误，添加教师失败");
            }
        }


        return resultMap;
    }

    /**
     * 删除单个教师
     *
     * @return
     * @parame:
     */
    @RequestMapping("/removeTeacher")
    @ResponseBody
    public Map<String, Object> removeTeacher(@RequestParam(value = "Id") Integer id) {
        boolean removeStatus = teacherService.removeTeacher(id);
        if (!removeStatus) {
            resultMap.put("status", "500");
            resultMap.put("message", "删除失败");
        } else {
            resultMap.put("status", "200");
            resultMap.put("message", "删除成功");
            resultMap.put("t_list", teacherService.selectAllTeacher());
        }
        return resultMap;

    }

    /**
     * 更新用户信息
     *
     * @param teacher
     * @return
     */
    @RequestMapping("/updateUserAccount")
    @ResponseBody
    public Map<String, Object> updateUserAccount(TTeacher teacher) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tName", teacher.gettName());
        parameters.put("tPass", teacher.gettPass());
        parameters.put("tIsAdmin", teacher.gettIsAdmin());
        Integer affectCount = teacherService.updateAccountByUsername(parameters);
        if (affectCount == 1) {
            resultMap.put("status", 200);
            resultMap.put("message", "alter success");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "alter failed");
        }
        return resultMap;
    }

    /* *//**
     * 判断是否只有Admin用户
     *
     *//*
    @RequestMapping("/onlyAdmin")
    @ResponseBody
    public boolean onlyAdmin(){
        return teacherService.selectCountOtherAdminExceptAdmin();
    }*/

    /**
     * 管理员修改教师的相关信息layer弹窗内容
     *
     * @return
     * @parame:
     */
    @RequestMapping("/admin_Teacher_Edit")
    public ModelAndView admin_Teacher_Edit(@RequestParam(value = "Id") int Id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("oneTeacher", teacherService.selectTeacherById(Id));
        mv.setViewName("admin_teacher_edit");
        return mv;
    }

    /**
     * 根据Id编辑单个教师的信息
     *
     * @return
     * @parame:
     */
    @RequestMapping(value = "/updateTeacherById")
    @ResponseBody

    public Map<String, Object> updateTeacherById(TTeacher teacher) {

        /*
         * 判断信息是否变动，变动则更新未变动不更新
         * */
        TTeacher teacherintable = teacherService.selectTeacherById((int) teacher.getId());

        if (teacherintable.gettName().equals(teacher.gettName()) && teacherintable.gettPass().equals(teacher.gettPass()) &&
                teacherintable.gettRealName().equals(teacher.gettRealName()) && teacherintable.gettIsAdmin() == teacher.gettIsAdmin()) {
            resultMap.put("status", "500");
            resultMap.put("message", "编辑信息未变动,请重新编辑");
        } else {
            /*
             * 编辑信息改变后向数据库写入*/
            Boolean b = teacherService.updateTeacherById(teacher);
            if (b) {
                resultMap.put("status", "200");
                resultMap.put("message", "编辑成功");

            } else {
                resultMap.put("status", "500");
                resultMap.put("message", "编辑失败，请重试");
            }
        }
        return resultMap;
    }


}

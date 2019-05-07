package com.examine.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SystemRealmConfig {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemRealmConfig.class);

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        logger.info("shiro filter factory bean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //设置静态数据
        filterChainDefinitionMap.put("/static/**", "anon");
        //设置退出
        filterChainDefinitionMap.put("/logout", "logout");

        //公共（管理员，教师）
        filterChainDefinitionMap.put("/editPassword","perms[/editPassword]");
        filterChainDefinitionMap.put("/checkPasswordByname","perms[/checkPasswordByname]");
        //教师的URL权限设置
        filterChainDefinitionMap.put("/teacher_exam_before","perms[/teacher_exam_before]");
        filterChainDefinitionMap.put("/teacher_exam_after","perms[/teacher_exam_after]");
        filterChainDefinitionMap.put("/teacher_manage_summary","perms[/teacher_manage_summary]");
        filterChainDefinitionMap.put("/teacher_manage_student","perms[/teacher_manage_student]");
        filterChainDefinitionMap.put("/teacher_manage_unlock","perms[/teacher_manage_unlock]");
        filterChainDefinitionMap.put("/teacher_manage_notify","perms[/teacher_manage_notify]");
        filterChainDefinitionMap.put("/teacher_exam_modify","perms[/teacher_exam_modify]");
        filterChainDefinitionMap.put("/submitTeacherLogin","perms[/submitTeacherLogin]");
        filterChainDefinitionMap.put("/teacher_main","perms[/teacher_main]");
        filterChainDefinitionMap.put("/importStudentInfo","perms[/importStudentInfo]");
        filterChainDefinitionMap.put("/exportSubmitInfo","perms[/exportSubmitInfo]");
        filterChainDefinitionMap.put("/downLoadZipFile","perms[/downLoadZipFile]");
        filterChainDefinitionMap.put("/saveStudent","perms[/saveStudent]");
        filterChainDefinitionMap.put("/updateDirtyData","perms[/updateDirtyData]");
        filterChainDefinitionMap.put("/doUnbinding","perms[/doUnbinding]");
        filterChainDefinitionMap.put("/insertMessage","perms[/insertMessage]");
        filterChainDefinitionMap.put("/selectAllMessage","perms[/selectAllMessage]");
        filterChainDefinitionMap.put("/examinfo_modifier","perms[/examinfo_modifier]");
        filterChainDefinitionMap.put("/updateExamInfo","perms[/updateExamInfo]");
        filterChainDefinitionMap.put("/startExam","perms[/startExam]");
        filterChainDefinitionMap.put("/stopExam","perms[/stopExam]");
        filterChainDefinitionMap.put("/uploadExamPaper","perms[/uploadExamPaper]");
        filterChainDefinitionMap.put("/saveExam","perms[/saveExam]");
        filterChainDefinitionMap.put("/clearExam","perms[/clearExam]");

        //学生的URL权限设置
        filterChainDefinitionMap.put("/success","perms[/success]");
        filterChainDefinitionMap.put("/studentListdir","perms[/studentListdir]");
        filterChainDefinitionMap.put("/student_exam_upload","perms[/student_exam_upload]");
        filterChainDefinitionMap.put("/studentsubmit","perms[/studentsubmit]");
        filterChainDefinitionMap.put("/studentdoloadpage","perms[/studentdoloadpage]");
        //管理员的URL权限设置

        filterChainDefinitionMap.put("/admin_main","perms[/admin_main]");
        filterChainDefinitionMap.put("/admin_teacher","perms[/admin_teacher]");
        filterChainDefinitionMap.put("/saveTeacher","perms[/saveTeacher]");
        filterChainDefinitionMap.put("/removeTeacher","perms[/removeTeacher]");
        filterChainDefinitionMap.put("/updateUserAccount","perms[/updateUserAccount]");
        filterChainDefinitionMap.put("/admin_Teacher_Edit","perms[/admin_Teacher_Edit]");
        filterChainDefinitionMap.put("/updateTeacherById","perms[/updateTeacherById]");
        filterChainDefinitionMap.put("/admin_exam","perms[/admin_exam]");
        filterChainDefinitionMap.put("/clearExam","perms[/clearExam]");
        filterChainDefinitionMap.put("/systemconfig","perms[/systemconfig]");
        filterChainDefinitionMap.put("/admin_config","perms[/admin_config]");

        //test
       // filterChainDefinitionMap.put("/success","perms[/test20]");

        shiroFilterFactoryBean.setLoginUrl("/login");

        //shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "systemRealm")
    public SystemRealm getSystemRealm() {
        return new SystemRealm();
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("systemRealm") SystemRealm systemRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(systemRealm);
        return securityManager;
    }
}
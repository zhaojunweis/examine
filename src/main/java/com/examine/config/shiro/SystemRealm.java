package com.examine.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.examine.domain.TPerm;
import com.examine.domain.TRole;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.StudentService;
import com.examine.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SystemRealm extends AuthorizingRealm {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemRealm.class);

    /**
     * 授权,对学生和教师进行分别权限设置
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("授权===================================================开始");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        TStudent student = null;
        TTeacher teacher = null;
        Object o = principals.getPrimaryPrincipal();
        if (o instanceof TStudent) {
            student = (TStudent) principals.getPrimaryPrincipal();
        } else {
            teacher = (TTeacher) principals.getPrimaryPrincipal();
        }
        Collection<String> permCollection = new HashSet<>();
        if (null != student) {
            TRole role = student.getRole();
            for (TPerm tPerm : role.getPermSet()) {
                permCollection.add(tPerm.getUrl());
            }
            info.addRole("student");
        }

        if (null != teacher) {
            TRole role = teacher.getRole();
            for (TPerm tPerm : role.getPermSet()) {
                permCollection.add(tPerm.getUrl());
            }
            List<String> roles = new ArrayList<>();
            info.addRoles(roles);
        }
        info.addStringPermissions(permCollection);
        logger.info("授权===================================================结束");
        return info;
    }

    /**
     * 认证(教师和学生角色进行分别验证)
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("认证===================================================开始");

        AuthenticationInfo info = null;

        Object principal = SecurityUtils.getSubject().getPrincipal();

        String username = (String) authenticationToken.getPrincipal();

        if(principal instanceof TStudent){
            TStudent student = studentService.selectStudentRoleAndPerm(username);
        }else{
            TTeacher teacher = teacherService.selectTeacherRoleAndPerm(username);
        }

        TStudent student = studentService.selectStudentRoleAndPerm(username);

        TTeacher teacher = teacherService.selectTeacherRoleAndPerm(username);

        if (student == null && teacher == null) {
            return null;
        }

        if (student != null) {
            info = new SimpleAuthenticationInfo(
                    student, student.getsName(), this.getClass().getSimpleName()
            );
        }

        if (teacher != null) {
            info = new SimpleAuthenticationInfo(
                    teacher, teacher.gettPass(), this.getClass().getSimpleName()
            );
        }
        logger.info("认证===================================================结束");
        return info;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}

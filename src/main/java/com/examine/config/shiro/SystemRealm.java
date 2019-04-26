package com.examine.config.shiro;

import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.StudentService;
import com.examine.service.TeacherService;
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

public class SystemRealm extends AuthorizingRealm {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemRealm.class);

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("授权配置=================开始");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        TStudent student = (TStudent) principals.getPrimaryPrincipal();

        TTeacher teacher = (TTeacher) principals.getPrimaryPrincipal();

        //存储权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //需要查询权限列表permission role
        if(null != student){
            System.out.println("学生");
        }

        if(null != teacher){
            System.out.println("管理员");
        }

        logger.info("授权配置=================结束");
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("认证==================开始");

        AuthenticationInfo info = null;

        String username = (String) authenticationToken.getPrincipal();

        TStudent student = studentService.selectStudentEntityByUsername(username);

        TTeacher teacher = teacherService.selectTeacherEntityByUsername(username);

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
        logger.info("认证==================结束");
        return info;
    }
}

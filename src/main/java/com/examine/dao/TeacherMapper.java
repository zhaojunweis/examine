package com.examine.dao;

import com.examine.domain.TTeacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TeacherMapper {
    /**
     * 通过名字筛选教师密码
     *
     * @param tName
     * @return
     */
    String selectTeacherPasswordByUsername(String tName);

    /**
     * 通过登陆信息筛选管理员密码
     *
     * @param tName
     * @return
     */
    String selectAdminByLoginMessage(String tName);

    /**
     * 更新教师信息
     *
     * @param map
     */
    Integer updateAccountByUsername(Map<String, Object> map);

    /**
     * 新建教师信息
     *
     * @param tTeacher
     * @return
     */
    long saveTeacher(TTeacher tTeacher);

    /**
     * 获取所有教师
     *
     * @return
     */
    List<TTeacher> selectAllTeacher();

    /**
     * 通过用户名声删除教师
     *
     * @param tName
     */
    void removeTeacher(String tName);

    /**
     * 通过考试名称清除考试信息
     *
     * @param examName
     * @return
     */
    Integer selectIsPigeonhole(String examName);
}

package com.examine.dao;

import com.examine.domain.TExam;
import com.examine.domain.TStudent;
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
     * @param id
     * @return
     */
    Integer removeTeacherById(Integer id);

    /**
     * 通过考试名称清除考试信息
     *
     * @param id
     * @return
     */
    Integer selectIsPigeonhole(Integer id);

    /**
     * 查询费Admin的管理员
     *
     * @return
     */
    Integer selectCountOtherAdminExceptAdmin();

    /**
     * 通过ID查询教师信息
     *
     * @return
     */
    TTeacher selectTeacherById(Integer id);

    /**
     * 通过教师名称查询实体
     *
     * @param tName
     * @return
     */
    TTeacher selectTeacherEntityByUsername(String tName);

    /**
     * 根据教师ID更新教师信息
     *
     * @param tTeacher
     * @return
     */
    Integer updateTeacherById(TTeacher tTeacher);

    /**
     * 获取教师的所有权限
     *
     * @param tName
     * @return
     */
    TTeacher selectTeacherRoleAndPerm(String tName);

    /**
     * 查询该场考试对应的学生数量
     * @param examId
     * @return
     */
    Integer selectCountByExamId(Integer examId);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<TStudent> selectByLimit(Map map);

    /**
     * 查询考前考试数量
     * @param tname
     * @return
     */
    Integer selectCountExamBefore(String tname);

    /**
     * 查询考后考试数量
     * @param tname
     * @return
     */
    Integer selectCountExamAfter(String tname);


    /**
     *  考前操作的考試分頁
     * @param map
     * @return
     */
    List<TExam> selectExamLimitBefore(Map map);

    /**
     *  考後操作的考試分頁
     * @param map
     * @return
     */
    List<TExam> selectExamLimitAfter(Map map);
}

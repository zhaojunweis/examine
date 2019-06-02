package com.examine.dao;

import com.examine.domain.TStudent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StudentMapper {

    /**
     * 获取登陆密码
     *
     * @param sSno
     * @return
     */
    String selectStudentPasswordByUsername(String sSno);

    /**
     * 获取客户端登陆Ip地址
     *
     * @param sSno
     * @return
     */
    String selectIpAddressByUsername(String sSno);

    /**
     * 获取学生实体信息
     *
     * @param sSno
     * @return
     */
    TStudent selectStudentEntityByUsername(String sSno);

    /**
     * 非物理删除学生信息
     *
     * @return
     */
    Integer nonphysicalDeleteStudents(Integer Id);

    /**
     * 按照记录添加学生信息
     *
     * @param student
     */
    void saveStudentOneByOne(TStudent student);

    /**
     * 查看参加某场考试学生数量
     *
     * @return
     */
    Integer examineesCount(String sScoreName);

    /**
     * 查看已经提交的数量
     *
     * @return
     */
    Integer examineesWhoSubmitCount(String sScoreName);

    /**
     * 未登陆的学生人数
     *
     * @return
     */
    Integer examineesWhoNoLoginCount(String sScoreName);

    /**
     *登录的学生人数
     *
     * @return
     */
    Integer examineesWhoHasLoginCount(String sScoreName);

    /**
     * 教师手动添加学生信息
     *
     * @param student
     * @return
     */
    Integer insertStudent(TStudent student);

    /**
     * 教师更改学生信息中的脏数据
     *
     * @return
     */
    Integer updateDirtyData(TStudent student);

    /**
     * 学生提交答案
     *
     * @param map
     * @return
     */
    Integer submitAnswer(Map<String,String> map);

    /**
     * 根据学号筛选学生权限信息
     *
     * @param sSno
     * @return
     */
    TStudent selectStudentRoleAndPerm(String sSno);

    /**
     * 关联IP
     *
     * @param sSno
     * @return
     */
    TStudent selectIpByStudentId(String sSno);

    /**
     * 通过IP查询学生信息
     *
     * @param ip
     * @return
     */
    TStudent selectStudentByIp(String ip);

    /**
     * 删除某次考试学生
     * @param studentId
     * @return
     */
    boolean deleteOneStudent(Integer studentId);
}

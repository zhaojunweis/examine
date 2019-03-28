package com.examine.dao;

import com.examine.domain.TStudent;
import org.springframework.stereotype.Component;

import java.util.List;

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
    Integer nonphysicalDeleteStudents();

    /**
     * 按照记录添加学生信息
     *
     * @param student
     */
    void saveStudentOneByOne(TStudent student);

    /**
     * 查看学生数量
     *
     * @return
     */
    Integer examineesCount();

    /**
     * 查看已经提交的数量
     *
     * @return
     */
    Integer examineesWhoSubmitCount();

    /**
     * 未登陆的学生信息
     *
     * @return
     */
    List<TStudent> examineesWhoNoLogin();

    /**
     *登录的学生信息
     *
     * @return
     */
    List<TStudent> examineesWhoHasLogin();
}

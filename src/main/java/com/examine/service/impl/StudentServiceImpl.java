package com.examine.service.impl;

import com.examine.common.util.ExcelUtils;
import com.examine.common.util.StringUtils;
import com.examine.dao.StudentMapper;
import com.examine.domain.TStudent;
import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 通过学号查询学生密码
     *
     * @param sSno
     * @return
     */
    @Override
    public String selectStudentPasswordByUsername(String sSno) {

        return studentMapper.selectStudentPasswordByUsername(sSno);
    }

    /**
     * 查询Ip地址是否使用
     *
     * @param sSno
     * @return
     */
    @Override
    public String selectIpAddressByUsername(String sSno) {

        return studentMapper.selectIpAddressByUsername(sSno);
    }

    /**
     * 通过学生学号查询学生实体
     *
     * @param sSno
     * @return
     */
    @Override
    public TStudent selectStudentEntityByUsername(String sSno) {

        return studentMapper.selectStudentEntityByUsername(sSno);
    }

    /**
     * 非物理删除学生
     *
     * @return
     */
    @Override
    public boolean nonphysicalDeleteStudents(Integer Id) {
        boolean flag = false;
        Integer affectCount = studentMapper.nonphysicalDeleteStudents(Id);
        if (affectCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 将学生信息导入到MYSQL数据库中
     *
     * @param localExcelPath
     * @return
     */
    @Override
    public boolean importStudentInfo(String localExcelPath) {
        boolean insertFlag = false;
        List<TStudent> students = ExcelUtils.importExcelToSQL(localExcelPath);
        if (students.size() > 0) {
            for (TStudent student : students) {
                studentMapper.saveStudentOneByOne(student);
            }
            insertFlag = true;
        }
        return insertFlag;
    }

    /**
     * 插入学生信息
     *
     * @param student
     * @return
     */
    @Override
    public boolean insertStudent(TStudent student) {
        boolean insertResult = false;
        Integer insertCount = studentMapper.insertStudent(student);
        if (insertCount == 1) {
            insertResult = true;
        }
        return insertResult;
    }

    /**
     * 更新脏数据
     *
     * @param student
     * @return
     */
    @Override
    public boolean updateDirtyData(TStudent student) {
        boolean updateResult = false;
        Integer updateCount = studentMapper.updateDirtyData(student);
        if (updateCount == 1) {
            updateResult = true;
        }
        return updateResult;
    }

    /**
     * 学生提交答案
     *
     * @param map
     * @return
     */
    @Override
    public boolean submitAnswer(Map<String, String> map) {
        boolean updateStatus = false;
        Integer affectCount = studentMapper.submitAnswer(map);
        if (affectCount == 1) {
            updateStatus = true;
        }
        return updateStatus;
    }

    /**
     * 参加考试学生数量
     *
     * @param sScoreName
     * @return
     */
    @Override
    public Integer examineesCount(String sScoreName) {

        return studentMapper.examineesCount(sScoreName);
    }

    /**
     * 学生考试进行过程中的信息
     *
     * @param sScoreName
     * @return
     */
    @Override
    public Map<String, Integer> studentCountOneExam(String sScoreName) {

        HashMap<String, Integer> resultMap = new HashMap<>();
        //考试参加人数
        Integer examineesCount = studentMapper.examineesCount(sScoreName);

        //登陆人数
        Integer loginCount = studentMapper.examineesWhoHasLoginCount(sScoreName);

        //未登录人数
        Integer noLoginCount = studentMapper.examineesWhoNoLoginCount(sScoreName);

        //提交人数
        Integer whoSubmitCount = studentMapper.examineesWhoSubmitCount(sScoreName);

        resultMap.put("examineesCount", examineesCount);
        resultMap.put("loginCount", loginCount);
        resultMap.put("noLoginCount", noLoginCount);
        resultMap.put("whoSubmitCount", whoSubmitCount);

        return resultMap;
    }

    /**
     * 通过学生学号查询学生权限信息
     *
     * @param sSno
     * @return
     */
    @Override
    public TStudent selectStudentRoleAndPerm(String sSno) {

        return studentMapper.selectStudentRoleAndPerm(sSno);
    }

    /**
     * 通过用户IP查询学生绑定信息
     *
     * @param ip
     * @return
     */
    @Override
    public TStudent selectStudentByIp(String ip) {

        return studentMapper.selectStudentByIp(ip);
    }
}

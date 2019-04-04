package com.examine.service.impl;

import com.examine.common.util.ExcelUtils;
import com.examine.common.util.StringUtils;
import com.examine.dao.StudentMapper;
import com.examine.domain.TStudent;
import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
    public boolean nonphysicalDeleteStudents() {
        boolean flag = false;
        Integer affectCount = studentMapper.nonphysicalDeleteStudents();
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
     * 学生查看已经提交的试题卷
     *
     * @param sSno
     * @return
     */
    @Override
    public Map<String,String> selectHasSubmitPaper(String sSno) {
        Map<String,String> submit = new HashMap<>();
        String paper = studentMapper.selectHasSubmitPaper(sSno);
        File paperUrl = null;
        String name = "";
        if (paper.trim().length() > 0) {
            paperUrl = new File(paper);
            name = paperUrl.getName();
        }else{
            name = "default";
        }
        //考虑到要点击查看，设置为name，paper路径
        submit.put(name,paper);
        return submit;
    }
}

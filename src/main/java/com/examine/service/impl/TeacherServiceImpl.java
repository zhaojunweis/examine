package com.examine.service.impl;

import com.examine.dao.SubmitMapper;
import com.examine.dao.TeacherMapper;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;
import com.examine.service.ExamService;
import com.examine.service.StudentService;
import com.examine.service.SubmitService;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    private final ExamService examService;

    private final StudentService studentService;

    private final SubmitService submitService;

    private final SubmitMapper submitMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper, ExamService examService, StudentService studentService, SubmitService submitService, SubmitMapper submitMapper) {
        this.teacherMapper = teacherMapper;
        this.examService = examService;
        this.studentService = studentService;
        this.submitService = submitService;
        this.submitMapper = submitMapper;
    }

    @Override
    public String selectTeacherPasswordByUsername(String tName) {

        return teacherMapper.selectTeacherPasswordByUsername(tName);
    }

    @Override
    public String selectAdminByLoginMessage(String tName) {

        return teacherMapper.selectAdminByLoginMessage(tName);
    }

    @Override
    public Integer updateAccountByUsername(Map<String, Object> map) {

        return teacherMapper.updateAccountByUsername(map);
    }

    /**
     * 添加教师信息
     *
     * @param tTeacher
     * @return
     */
    @Override
    public boolean saveTeacher(TTeacher tTeacher) {
        boolean flag = false;
        long index = teacherMapper.saveTeacher(tTeacher);
        if (index > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询所有教师
     *
     * @return
     */
    @Override
    public List<TTeacher> selectAllTeacher() {

        return teacherMapper.selectAllTeacher();
    }

    /**
     * 删除教师
     *
     * @param tName
     */
    @Override
    public void removeTeacher(String tName) {

        teacherMapper.removeTeacher(tName);
    }

    /**
     * 清除考试信息
     *
     * @param examName
     * @return
     */
    @Override
    public boolean clearExamInfo(String examName) {
        boolean flag = false;
        //查询是否归档
        Integer isPigeonhole = teacherMapper.selectIsPigeonhole(examName);
        if (isPigeonhole == 1) {
            //非物理删除考试信息
            boolean examDelete = examService.nonphysicalDeleteOneExamByName(examName);
            //非物理删除学生信息
            boolean studentDelete = studentService.nonphysicalDeleteStudents();
            //非物理删除提交信息
            boolean submitDelete = submitService.nonphysicalDeleteAllSubmit();
            //删除成功
            if (examDelete && studentDelete && submitDelete) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 导出学生提交信息
     *
     * @return
     */
    @Override
    public List<TStudent> exportSubmitInfo() {

        return submitMapper.studentSubmitInfo();
    }
}

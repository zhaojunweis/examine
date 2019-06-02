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

    /**
     * 通过名称选择教师密码
     *
     * @param tName
     * @return
     */
    @Override
    public String selectTeacherPasswordByUsername(String tName) {

        return teacherMapper.selectTeacherPasswordByUsername(tName);
    }

    /**
     * 获取管理员登陆信息
     *
     * @param tName
     * @return
     */
    @Override
    public String selectAdminByLoginMessage(String tName) {

        return teacherMapper.selectAdminByLoginMessage(tName);
    }

    /**
     * 更通过名称更新账号
     *
     * @param map
     * @return
     */
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
     * 根据ID删除教师
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeTeacher(Integer id) {
        boolean flag = false;
        Integer affectCount = teacherMapper.removeTeacherById(id);
        if(affectCount == 1){
            flag = true;
        }
        return flag;
    }

    /**
     * 清除考试信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean clearExamInfo(Integer id) {
        boolean flag = false;
        //查询是否归档
        Integer isPigeonhole = teacherMapper.selectIsPigeonhole(id);
        if (isPigeonhole == 1) {
            //非物理删除学生信息
            boolean studentDelete = studentService.nonphysicalDeleteStudents(id);
            //非物理删除提交信息
            boolean submitDelete = submitService.nonphysicalDeleteAllSubmit(id);
            //删除成功
            if (studentDelete && submitDelete) {
                    //非物理删除考试信息
                   boolean examDelete = examService.nonphysicalDeleteOneExamById(id);
                   if (examDelete){
                       flag = true;
                   }
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

    /**
     * 判断是否只有admin
     *
     * @return
     */
    @Override
    public boolean selectCountOtherAdminExceptAdmin() {
        boolean flag =  false;
        int affectCount = teacherMapper.selectCountOtherAdminExceptAdmin();

        if(affectCount >= 1){
            flag = true;
        }
        return flag;
    }

    /**
     * 通过教师Id查询教师信息
     *
     * @return
     */
    @Override
    public TTeacher selectTeacherById(Integer id) {

        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 通过教师ID更新教师信息
     *
     * @param tTeacher
     * @return
     */
    @Override
    public boolean updateTeacherById(TTeacher tTeacher) {
        boolean flag = false;
        Integer affectCount = teacherMapper.updateTeacherById(tTeacher);
        if(affectCount == 1){
            flag = true;
        }
        return flag;
    }

    /**
     * 通过教师姓名查询教师实体信息
     *
     * @param tName
     * @return
     */
    @Override
    public TTeacher selectTeacherEntityByUsername(String tName) {

        return teacherMapper.selectTeacherEntityByUsername(tName);
    }

    /**
     * 通过教师姓名查询教师权限
     *
     * @param tName
     * @return
     */
    @Override
    public TTeacher selectTeacherRoleAndPerm(String tName) {

        return teacherMapper.selectTeacherRoleAndPerm(tName);
    }

    /**
     * 查询该场考试对应的学生数量
     * @param examId
     * @return
     */
    @Override
    public Integer selectCountByExamId(Integer examId) {
        return teacherMapper.selectCountByExamId(examId);
    }

    /**
     * 分页查询
     * @param map
     * @return
     */
    @Override
    public List<TStudent> selectByLimit(Map map) {
        return teacherMapper.selectByLimit(map);
    }
}

package com.examine.service;

import com.examine.domain.TExam;
import com.examine.domain.TStudent;
import com.examine.domain.TTeacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    String selectTeacherPasswordByUsername(String tName);

    String selectAdminByLoginMessage(String tName);

    Integer updateAccountByUsername(Map<String,Object> map);

    boolean saveTeacher(TTeacher tTeacher);

    List<TTeacher> selectAllTeacher();

    boolean removeTeacher(Integer id);

    boolean clearExamInfo(Integer id);

    List<TStudent> exportSubmitInfo();

    boolean selectCountOtherAdminExceptAdmin();

    TTeacher selectTeacherById(Integer id);

    boolean updateTeacherById(TTeacher tTeacher);

    TTeacher selectTeacherEntityByUsername(String tName);

    TTeacher selectTeacherRoleAndPerm(String tName);

    Integer selectCountByExamId(Integer examId);

    List<TStudent> selectByLimit(Map map);

    Integer selectCountExamBefore(String tname);

    Integer selectCountExamAfter(String tname);

    List<TExam> selectExamLimitBefore(Map map);

    List<TExam> selectExamLimitAfter(Map map);

    boolean isAdmin(String tName);

    Integer selectAllExamCount();

    List<TExam> selectAllExamLimit(Map map);

    Integer selectExamIdByExamName(String examName);
}

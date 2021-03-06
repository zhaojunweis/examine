package com.examine.service;

import com.examine.domain.TStudent;

import java.util.Map;

public interface StudentService {

    String selectStudentPasswordByUsername(String sSno);

    String selectIpAddressByUsername(String sSno);

    TStudent selectStudentEntityByUsername(String sSno);

    boolean nonphysicalDeleteStudents(Integer Id);

    boolean importStudentInfo(String localExcelPath,Integer examId);

    boolean insertStudent(TStudent student);

    boolean updateDirtyData(TStudent student);

    boolean submitAnswer(Map<String,String> map);

    Integer examineesCount(String sScoreName);

    Map<String,Integer> studentCountOneExam(String sScoreName);

    TStudent selectStudentRoleAndPerm(String sSno);

    TStudent selectStudentByIp(String ip);

    boolean deleteOneStudent(Integer studentId);
}

package com.examine.service;

import com.examine.domain.TStudent;

public interface StudentService {

    String selectStudentPasswordByUsername(String sSno);

    String selectIpAddressByUsername(String sSno);

    TStudent selectStudentEntityByUsername(String sSno);

    boolean nonphysicalDeleteStudents();

    boolean importStudentInfo(String localExcelPath);

    boolean insertStudent(TStudent student);

    boolean updateDirtyData();
}

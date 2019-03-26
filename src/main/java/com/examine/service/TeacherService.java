package com.examine.service;

import com.examine.domain.TTeacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    String selectTeacherPasswordByUsername(String tName);

    String selectAdminByLoginMessage(String tName);

    Integer updateAccountByUsername(Map<String,Object> map);

    boolean saveTeacher(TTeacher tTeacher);

    List<TTeacher> selectAllTeacher();

    void removeTeacher(String tName);
}

package com.examine.service;

public interface TeacherService {
    String selectTeacherPasswordByUsername(String tName);

    String selectAdminByLoginMessage(String tName);
}

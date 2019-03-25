package com.examine.dao;

import org.springframework.stereotype.Component;

@Component
public interface TeacherMapper {
   String selectTeacherPasswordByUsername(String tName);

   String selectAdminByLoginMessage(String tName);
}

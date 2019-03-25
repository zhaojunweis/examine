package com.examine.dao;

import com.examine.domain.TTeacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TeacherMapper {
   String selectTeacherPasswordByUsername(String tName);

   String selectAdminByLoginMessage(String tName);

   void updateAccountByUsername(Map<String,Object> map);

   long saveTeacher(TTeacher tTeacher);

   List<TTeacher> selectAllTeacher();

   void removeTeacher(String tName);
}

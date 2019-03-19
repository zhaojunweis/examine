package com.examine.dao;

import org.springframework.stereotype.Component;

@Component
public interface StudentMapper {

   String selectStudentPasswordByUsername(String sSno);
}

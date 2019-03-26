package com.examine.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface SubmitMapper {
   void insertStudentLoginMessage(Map<String,String> map);
}

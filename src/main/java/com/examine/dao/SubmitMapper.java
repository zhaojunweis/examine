package com.examine.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface SubmitMapper {
   /**
    * 插入登陆信息到t_submit
    * @param map
    */
   void insertStudentLoginMessage(Map<String,String> map);
}

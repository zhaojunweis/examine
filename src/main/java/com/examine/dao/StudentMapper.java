package com.examine.dao;

import com.examine.domain.TStudent;
import org.springframework.stereotype.Component;

@Component
public interface StudentMapper {

   /**
    * 获取登陆密码
    * @param sSno
    * @return
    */
   String selectStudentPasswordByUsername(String sSno);

   /**
    * 获取客户端登陆Ip地址
    * @param sSno
    * @return
    */
   String selectIpAddressByUsername(String sSno);

   /**
    * 获取学生实体信息
    * @param sSno
    * @return
    */
   TStudent selectStudentEntityByUsername(String sSno);
}

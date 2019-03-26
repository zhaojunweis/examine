package com.examine.service.impl;

import com.examine.dao.TeacherMapper;
import com.examine.domain.TTeacher;
import com.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public String selectTeacherPasswordByUsername(String tName) {

        return teacherMapper.selectTeacherPasswordByUsername(tName);
    }

    @Override
    public String selectAdminByLoginMessage(String tName) {

        return teacherMapper.selectAdminByLoginMessage(tName);
    }

    @Override
    public Integer updateAccountByUsername(Map<String, Object> map) {

       return teacherMapper.updateAccountByUsername(map);
    }

    @Override
    public boolean saveTeacher(TTeacher tTeacher) {
        boolean flag = false;
        long index = teacherMapper.saveTeacher(tTeacher);
        if(index > 0){
           flag = true;
        }
        return flag;
    }

    @Override
    public List<TTeacher> selectAllTeacher() {

        return teacherMapper.selectAllTeacher();
    }

    @Override
    public void removeTeacher(String tName) {

        teacherMapper.removeTeacher(tName);
    }
}

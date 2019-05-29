package com.examine.service;

import com.examine.domain.TExam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    boolean nonphysicalDeleteOneExamById(Integer id);

    boolean saveExaminationInfo(TExam exam);

    Integer uploadExamPaper(Map<String,String> map);

    boolean stopOneExamById(Integer id);

    List<TExam> selectAllExamInfo();

    TExam selectOneExamInfoById(Integer id);

    boolean updateExamInfo(TExam exam);

    List<TExam> selectExamInfoByTName(String tName);

    boolean startExamById(Integer id);

    List<TExam> selectAutoStartExams();

    boolean isExistExam();

    List<TExam> selectBeforeExamInfo(String tName);

    List<TExam> selectAfterExamInfo(String tName);
}

package com.examine.service;

import com.examine.domain.TExam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    boolean nonphysicalDeleteOneExamByName(String examName);

    void saveExaminationInfo(TExam exam);

    Integer uploadExamPaper(Map<String,String> map);

    boolean stopExam(String examName);

    List<TExam> selectAllExamInfo();
}

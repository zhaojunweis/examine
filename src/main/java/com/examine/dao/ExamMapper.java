package com.examine.dao;

import com.examine.domain.TExam;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ExamMapper {
    /**
     * 非物理删除某一场考试
     *
     * @param examName
     * @return
     */
    Integer nonphysicalDeleteOneExamByName(String examName);

    /**
     * 新建考试
     *
     * @return
     */
    Integer saveExaminationInfo(TExam exam);

    /**
     * 上传试卷
     *
     * @return
     */
    Integer uploadExamPaper(Map<String,String> map);
}

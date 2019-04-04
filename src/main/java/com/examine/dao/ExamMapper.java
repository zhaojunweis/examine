package com.examine.dao;

import com.examine.domain.TExam;
import org.springframework.stereotype.Component;

import java.util.List;
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
    Integer uploadExamPaper(Map<String,String> paramMap);

    /**
     * 停止考试
     *
     * @param examName
     */
    Integer stopExam(String examName);

    /**
     * 查询所有考试信息
     *
     * @return
     */
    List<TExam> selectAllExamInfo();

    /**
     * 手动开启时候，设置考试时间为当前时间,并设置t_start为1
     *
     * @param examName
     * @return
     */
    Integer manualStart(String examName);

}

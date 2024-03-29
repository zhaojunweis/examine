package com.examine.dao;

import com.examine.domain.TExam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ExamMapper {
    /**
     * 通过ID非物理删除某一场考试
     *
     * @param id
     * @return
     */
    Integer nonphysicalDeleteOneExamById(Integer id);

    /**
     * 新建考试
     *
     * @return
     */
    Integer saveExaminationInfo(TExam exam);

    /**
     * 教师上传试卷
     *
     * @return
     */
    Integer uploadExamPaper(Map<String, String> map);

    /**
     * 停止考试
     *
     * @param id
     */
    Integer stopOneExamById(Integer id);

    /**
     * 查询所有考试信息
     *
     * @return
     */
    List<TExam> selectAllExamInfo();

    /**
     * 通过ID查询异常考试的信息
     *
     * @return
     */
    TExam selectOneExamInfoById(Integer id);

    /**
     * 更新考试信息
     *
     * @param exam
     * @return
     */
    Integer updateExamInfo(TExam exam);

    /**
     * 根据创建考试教师名称，实现考试信息查询
     *
     * @param tName
     * @return
     */
    List<TExam> selectExamInfoByTName(String tName);

    /**
     * 通过考试ID开启考试
     *
     * @param id
     * @return
     */
    Integer startExamById(Integer id);

    /**
     * 筛选考试自动开始并且is_finish为0的考试
     *
     * @return
     */
    List<TExam> selectAutoStartExams();

    /**
     * 是否已经有考试正在进行
     *
     * @return
     */
    Integer isExistExam();

    /**
     * 查询考前考试信息
     * @return
     */
    List<TExam> selectBeforeExamInfo(String tName);

    /**
     * 查询考试后的考试信息
     *
     * @return
     */
    List<TExam> selectAfterExamInfo(String tName);

    /**
     * 修改标志位t_isdelete
     * @param Id
     * @return
     */
    boolean finishedIsDelete(Integer Id);

    /**
     * 查询所有考试
     *
     * @return
     */
    List<TExam> selectAllExamsInfo();
}

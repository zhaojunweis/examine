package com.examine.service.impl;

import com.examine.dao.ExamMapper;
import com.examine.domain.TExam;
import com.examine.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;

    @Autowired
    public ExamServiceImpl(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    /**
     * 通过考试名称非物理删除一个考试
     *
     * @param examName
     * @return
     */
    @Override
    public boolean nonphysicalDeleteOneExamByName(String examName) {
        boolean flag = false;
        int affectCount = examMapper.nonphysicalDeleteOneExamByName(examName);
        if (affectCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建考试
     *
     * @param exam
     */
    @Override
    public boolean saveExaminationInfo(TExam exam) {
        boolean flag = false;
        long index = examMapper.saveExaminationInfo(exam);
        if (index > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 加载考试试题到服务器
     *
     * @param map
     * @return
     */
    @Override
    public Integer uploadExamPaper(Map<String, String> map) {

        return examMapper.uploadExamPaper(map);
    }

    /**
     * 停止考试
     *
     * @param examName
     */
    @Override
    public boolean stopExam(String examName) {
        boolean flag = false;
        Integer stopFlag = examMapper.stopExam(examName);
        if (stopFlag == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询所有考试信息
     *
     * @return
     */
    @Override
    public List<TExam> selectAllExamInfo() {

        return examMapper.selectAllExamInfo();
    }

    /**
     * 通过Id查询考试信息
     *
     * @return
     */
    @Override
    public TExam selectOneExamInfoById(Integer id) {

        return examMapper.selectOneExamInfoById(id);
    }

    /**
     * 更新考试信息
     *
     * @param exam
     * @return
     */
    @Override
    public boolean updateExamInfo(TExam exam) {
        boolean flag = false;
        if (examMapper.updateExamInfo(exam) == 1) {
            flag = true;
        }
        return flag;
    }
}

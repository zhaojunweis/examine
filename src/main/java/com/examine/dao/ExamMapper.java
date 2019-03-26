package com.examine.dao;

import org.springframework.stereotype.Component;

@Component
public interface ExamMapper {
    /**
     * 非物理删除某一场考试
     *
     * @param examName
     * @return
     */
    Integer nonphysicalDeleteOneExamByName(String examName);
}

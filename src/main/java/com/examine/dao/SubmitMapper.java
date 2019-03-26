package com.examine.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface SubmitMapper {
    /**
     * 插入登陆信息到t_submit
     *
     * @param map
     */
    void insertStudentLoginMessage(Map<String, String> map);

    /**
     * 非物理删除提交信息
     *
     * @return
     */
    Integer nonphysicalDeleteAllSubmit();
}

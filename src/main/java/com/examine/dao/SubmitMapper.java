package com.examine.dao;

import com.examine.domain.TStudent;
import org.springframework.stereotype.Component;

import java.util.List;
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
    Integer nonphysicalDeleteAllSubmit(Integer Id);

    /**
     * 学生提交信息
     *
     * @return
     */
    List<TStudent> studentSubmitInfo();

    /**
     * 下载所有学生提交的答案
     *
     * @return
     */
    List<String> downloadSubmitZip();


    /**
     * 解除绑定
     *
     * @param sSno
     * @return
     */
    Integer doUnbinding(String sSno);

    /**
     * 修改exam的pageonhole标志位,成功归档后改为1
     * @param id
     */
    boolean finishedPageOnHole(Integer id);
}

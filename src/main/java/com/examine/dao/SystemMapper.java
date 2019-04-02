package com.examine.dao;

import com.examine.domain.TSystem;
import org.springframework.stereotype.Component;

@Component
public interface SystemMapper {
    /**
     * 更新系统配置
     *
     * @param system
     * @return
     */
    int updateSystemConfigure(TSystem system);

    /**
     * 查询系统的默认配置
     *
     * @return
     */
    TSystem selectSystemConfigure();

    /**
     * 最大上传文件大小
     *
     * @return
     */
    Long selectMaxUploadSize();

    /**
     * 最小上传文件大小
     *
     * @return
     */
    Long selectMinUploadSize();

    /**
     * 考试时间
     *
     * @return
     */
    Integer selectExamTime();

    /**
     * 分页数量
     *
     * @return
     */
    Integer selectPageCount();

    /**
     * 手工开启时间
     *
     * @return
     */
    Integer selectManualOpenTime();
}

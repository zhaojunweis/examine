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
     * 获取学生上传文件最大值
     *
     * @return
     */
    long selectMaxUploadSize();

    /**
     * 获取学生上传文件最小值
     *
     * @return
     */
    long selectMinUploadSize();
}

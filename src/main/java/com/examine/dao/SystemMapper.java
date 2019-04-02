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
}

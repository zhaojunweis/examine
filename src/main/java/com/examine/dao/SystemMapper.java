package com.examine.dao;

import com.examine.domain.TSystem;
import org.springframework.stereotype.Component;

@Component
public interface SystemMapper {
    /**
     * 设置系统配置
     *
     * @param system
     * @return
     */
    int insertSystemConfigure(TSystem system);
}

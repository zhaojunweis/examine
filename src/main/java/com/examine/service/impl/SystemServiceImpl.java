package com.examine.service.impl;

import com.examine.dao.SystemMapper;
import com.examine.domain.TSystem;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    private final SystemMapper systemMapper;

    @Autowired
    public SystemServiceImpl(SystemMapper systemMapper) {
        this.systemMapper = systemMapper;
    }

    /**
     * 插入系统配置
     *
     * @param system
     * @return
     */
    @Override
    public boolean updateSystemConfigure(TSystem system) {
        boolean flag = false;
        int updateCount = systemMapper.updateSystemConfigure(system);
        if (updateCount == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询管理员系统配置
     *
     * @return
     */
    @Override
    public TSystem selectSystemConfigure() {

        return systemMapper.selectSystemConfigure();
    }

    /**
     * 查询最大加载大小
     *
     * @return
     */
    @Override
    public Long selectMaxUploadSize() {

        return systemMapper.selectMaxUploadSize();
    }

    /**
     * 查询最小加载大小
     *
     * @return
     */
    @Override
    public Long selectMinUploadSize() {

        return systemMapper.selectMinUploadSize();
    }

    /**
     * 查询考试的时间
     *
     * @return
     */
    @Override
    public Integer selectExamTime() {

        return systemMapper.selectExamTime();
    }

    /**
     * 查询分页数量
     *
     * @return
     */
    @Override
    public Integer selectPageCount() {

        return systemMapper.selectPageCount();
    }

    /**
     * 查询手动开启时间
     *
     * @return
     */
    @Override
    public Integer selectManualOpenTime() {

        return systemMapper.selectManualOpenTime();
    }
}

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
     * 获取系统文件最大上传
     *
     * @return
     */
    @Override
    public long selectMaxUploadSize() {

        return systemMapper.selectMaxUploadSize();
    }

    /**
     * 获取系统文件最小上传
     *
     * @return
     */
    @Override
    public long selectMinUploadSize() {

        return systemMapper.selectMinUploadSize();
    }

    /**
     * 获取系统考试时间
     *
     * @return
     */
    @Override
    public int selectSystemTimer() {

        return systemMapper.selectSystemTimer();
    }
}

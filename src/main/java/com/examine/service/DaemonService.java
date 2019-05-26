package com.examine.service;


public interface DaemonService {
    /**
     * 改变考试状态
     */
    void changeStatus();

    /**
     * 查询考试信息
     */
    void scanMySQL();

}

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

    /**
     * 开启考试扫描线程
     */
    void startThread();

    /**
     * 开启考试关闭线程
     */
    void cancelThread();
    /**
     * 如果程序不是第一次启动，只扫描一次
     */
    void scanOnce();
}

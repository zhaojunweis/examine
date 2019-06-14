package com.examine.service.impl;

import com.examine.common.util.DateUtil;
import com.examine.dao.ExamMapper;
import com.examine.domain.TExam;
import com.examine.service.DaemonService;
import com.examine.service.ExamService;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DaemonServiceImpl implements DaemonService, Runnable {

    private static List<TExam> examList;

    private static boolean cancel = true;

    private static Queue<TExam> examQueue = new ArrayDeque<>();

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamService examService;

    @Autowired
    private SystemService systemService;

    /**
     * 关闭线程
     */
    public void cancelThread() {
        cancel = false;
    }

    /**
     * 开启线程
     */
    @Override
    public void startThread() {
        cancel = true;
    }

    /**
     * 状态修改
     */
    @Override
    public void changeStatus() {
        System.out.println("change exam Status");
        run();
    }

    /**
     * 数据库扫描
     */
    @Override
    public void scanMySQL() {
        System.out.println("scanMySQL");
        Timer timer = new Timer();
        int period = 10 * 60 * 100;
        int delay = 0;
        //模拟第一次timer
        /*examList = examMapper.selectAutoStartExams();
        for (TExam exam : examList) {
            if (!examQueue.contains(exam)) {
                examQueue.add(exam);
            }
        }*/
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("123");
                examList = examMapper.selectAutoStartExams();
                for (TExam exam : examList) {
                    if (!examQueue.contains(exam)) {
                        examQueue.add(exam);
                    }
                }
            }
        }, delay, period);
    }

    /**
     * 考试扫描，开启考试并修改状态位
     */
    @Override
    public void run() {
        //boolean flag = true;
        //long timeMillis = 0;
        System.out.println("Open Daemon");
        while (cancel) {
            System.out.println("456");
            if (!examService.isExistExam()) {
                long systemTimer = systemService.selectSystemTimer();
                //timeMillis = systemTimer * 60 * 100;
                long currentTimer = System.currentTimeMillis();
                while(!examQueue.isEmpty()) {
                    TExam exam = ((ArrayDeque<TExam>) examQueue).pop();
                    String beginTimer = exam.getExamStartTime();
                    if (beginTimer != null) {
                        Date date = DateUtil.stringToDate(beginTimer, DateUtil.DATETIME_PATTERN);
                        long time = date.getTime();
                        if ((currentTimer >= time) && (currentTimer <= time + systemTimer * 60 * 1000)) {
                            boolean isSuccess = examService.startExamById((int) exam.getId());
                            if (isSuccess) {
                                //查询到之后，直接就终止线程
                                cancel = false;
                                return;
                            }
                        }
                    }
                }
                if(examQueue.isEmpty()){
                    cancel = false;
                }
            }else{
                cancel = false;
            }
        }
    }
}

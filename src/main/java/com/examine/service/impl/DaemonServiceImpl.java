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

    private static int isTimer = 0;

    private static int delay = 0;

    private static int period = 10 * 60 * 1000;

    private static List<TExam> examList;

    private static Queue<TExam> examQueue = new ArrayDeque<>();

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamService examService;

    @Autowired
    private SystemService systemService;

    @Override
    public void changeStatus() {
        if (isTimer == 0) {
            scanMySQL();
            ++isTimer;
        }
        run();
    }

    @Override
    public void scanMySQL() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("scanMySQL");
                examList = examMapper.selectAutoStartExams();
                for (TExam exam : examList) {
                    if (!examQueue.contains(exam)) {
                        examQueue.add(exam);
                    }
                }
            }
        }, delay, period);
    }

    @Override
    public void run() {
        boolean flag = true;
        //long timeMillis = 0;
        while (flag) {
            if (!examService.isExistExam()) {
                long systemTimer = systemService.selectSystemTimer();
                //timeMillis = systemTimer * 60 * 100;
                long currentTimer = System.currentTimeMillis();
                if (!examQueue.isEmpty()) {
                    TExam exam = ((ArrayDeque<TExam>) examQueue).pop();
                    String beginTimer = exam.getExamStartTime();
                    if (beginTimer != null) {
                        Date date = DateUtil.stringToDate(beginTimer, DateUtil.DATETIME_PATTERN);
                        long time = date.getTime();
                        if ((currentTimer >= time) && (currentTimer <= time + systemTimer)) {
                            boolean isSuccess = examService.startExamById((int) exam.getId());
                            if (isSuccess) {
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
    }
}

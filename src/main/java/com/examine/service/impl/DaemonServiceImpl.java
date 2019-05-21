package com.examine.service.impl;

import com.examine.common.util.DateUtil;
import com.examine.dao.ExamMapper;
import com.examine.domain.TExam;
import com.examine.service.DaemonService;
import com.examine.service.ExamService;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DaemonServiceImpl implements DaemonService, Runnable {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamService examService;

    @Autowired
    private SystemService systemService;

    @Override
    public void changeStatus() {
        //Handle.daemonService.run();
        run();
    }

    @Override
    public void run() {
        boolean flag = true;
        long timeMillis = 0;
        while (flag) {
            if (!examService.isExistExam()) {
                long systemTimer = systemService.selectSystemTimer();
                timeMillis = systemTimer * 60 * 100;
                long currentTimer = System.currentTimeMillis();
                List<TExam> examList = examMapper.selectAutoStartExams();
                for (TExam exam : examList) {
                    String beginTimer = exam.getExamStartTime();
                    if (beginTimer != null) {
                        Date date = DateUtil.stringToDate(beginTimer, DateUtil.DATETIME_PATTERN);
                        long time = date.getTime();
                        if ((currentTimer >= time) && (currentTimer <= time + systemTimer)) {
                            boolean isSuccess = examService.startExamById((int) exam.getId());
                            if (isSuccess) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

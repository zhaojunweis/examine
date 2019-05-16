package com.examine.test;

import com.examine.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

public class Daemon implements Runnable {

    @Autowired
    private ExamService examService;

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            System.out.println("考试");
            //1. 开启考试
            //筛选考试,跟现在的时间进行比较
            //如果时间到了，就让后台进程休眠系统设置的时间，即停止扫描
            //并开启考试

            //考试结束
            //2. 手动关闭考试
            //      1、修改考试标志位已经结束
            //      2、唤醒后台进程
            //3. 休眠时间到了30分钟，唤醒后台进程，修改考试标志位已经结束
            //
            int timerHere = 0;
            if(timerHere == 0){
                System.out.println("Java考试开始了");
                flag = false;
            }
        }
    }
}

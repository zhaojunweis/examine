package com.examine.test;

public class Daemon implements Runnable {

    //private ExamService examService = new ExamServiceImpl();

    @Override
    public void run() {


       /* boolean flag = true;
        while (flag) {
            System.out.println("考试");
            //1. 开启考试
            //筛选考试,跟现在的时间进行比较
            //如果时间到了，就让后台进程休眠系统设置的时间，即停止扫描
            //并开启考试
            //System.out.println(examService == null);
            //List<TExam> tExams = examService.selectAllExamInfo();
            TExam exam = new TExam();
            exam.settName("Java");
            exam.setExamStartTime("2019-05-21 12:31:00");
            exam.setIsAutoStart(1);

            if (exam.getExamStartTime().equals("2019-05-21 12:31:00") && exam.getIsAutoStart() == 1) {
               exam.setIsStart(1);
                try {
                    System.out.println("自动开启"+exam.gettName()+"开始");
                    exam.setIsStart(1);
                    Thread.sleep(1000);
                    exam.setIsFinished(1);
                    System.out.println(exam.getIsFinished());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //考试结束
            //2. 手动关闭考试
            //      1、修改考试标志位已经结束
            //      2、唤醒后台进程
            //3. 休眠时间到了30分钟，唤醒后台进程，修改考试标志位已经结束
            //
        }*/
    }


}

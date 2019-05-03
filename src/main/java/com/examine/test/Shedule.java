package com.examine.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Auther:wencaixu
 * @Date:2019/5/3
 * @Description:com.examine.test
 */
public class Shedule {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("123");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor() ;
        service.scheduleAtFixedRate(runnable,0,1,TimeUnit.MINUTES);
    }
}

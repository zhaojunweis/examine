package com.examine.test;


import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class TestIp {
    @Test
    public void testIp() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());
    }

    private static int count = 0;

    public static void timer(){
        Timer timer = new Timer();
        int period = 10 * 60 * 100;
        int delay = 0;
        System.out.println("121345");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println((count++)+"123");
            }
        }, delay, period);
    }


    @Test
    public void testTimer(){
        timer();
    }
}

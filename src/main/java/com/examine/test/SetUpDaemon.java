package com.examine.test;

import java.util.Scanner;

public class SetUpDaemon {

    public void setup(){
        Daemon daemon = new Daemon();
        Thread t = new Thread(daemon);
        //t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args) {
        new Daemon().start();
        System.out.println("===============================");
        new Daemon().start();
    }
}

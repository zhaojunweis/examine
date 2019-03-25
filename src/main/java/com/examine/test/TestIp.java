package com.examine.test;


import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIp {
    @Test
    public void testIp() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());
    }
}

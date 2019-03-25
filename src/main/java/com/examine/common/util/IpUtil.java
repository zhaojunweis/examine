package com.examine.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    private static InetAddress inetAddress = null;

    static {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static String getLocalIp() {
        return inetAddress.getHostAddress();
    }
}

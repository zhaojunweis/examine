package com.examine.config.site;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BundleReader {


    public static String getGlobalUrl() {
        String global_url = null;
        InputStream resourceAsStream =
                BundleReader.class.getResourceAsStream("/properties/configure.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        global_url = properties.getProperty("global_url");

        return global_url;
    }

    public static void main(String[] args) {
        System.out.println(BundleReader.getGlobalUrl());
    }

}

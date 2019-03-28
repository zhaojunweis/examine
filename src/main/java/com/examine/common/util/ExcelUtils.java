package com.examine.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtils {

    private static void importExcel(String fileUrl){
        try {
            FileInputStream fileStream = new FileInputStream(fileUrl);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package com.examine.test;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class TestExcelToMySQL {
    @Test
    public void testExcelToMySQL(){
        try {
            Workbook workbook = Workbook.getWorkbook(new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}

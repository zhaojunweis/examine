package com.examine.test;

import com.examine.common.util.ExcelUtils;
import org.junit.Test;

public class TestExcelToMySQL {

    @Test
    public void importExcel(){
        ExcelUtils.importExcelToSQL("C:\\Users\\lenovo\\Desktop\\name.xlsx");
    }

}

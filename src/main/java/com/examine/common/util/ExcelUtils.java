package com.examine.common.util;

import com.examine.config.SiteConfig;
import com.examine.domain.TStudent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    private String[] titles;
//    private String excelpath;

    public ExcelUtils(String[] titles) {

        this.titles = titles;
    }

    public static List<TStudent> importExcelToSQL(String fileUrl) {
        List<TStudent> students = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(fileUrl)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }
                TStudent student = new TStudent();
                student.setsSno(NumberToTextConverter.toText(row.getCell(0).getNumericCellValue()));
                student.setsName(row.getCell(1).getStringCellValue());
                //student.setsPass("123456"); // set default password
                Cell classId = row.getCell(2);
                if (classId.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    String classIdS = NumberToTextConverter.toText(classId.getNumericCellValue());
                    student.setsClassId(Integer.parseInt(classIdS));
                }
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void buildExcelDocument(List<TStudent> students,HttpServletResponse response) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        int rowIndex = 0;
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(rowIndex++);
        for(int i = 0; i < titles.length; i++){
            row.createCell(i).setCellValue(titles[i]);
        }
        for(TStudent student:students){
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(student.getsClassId());
            row.createCell(1).setCellValue(student.getsSno());
            row.createCell(2).setCellValue(student.getsName());
            row.createCell(3).setCellValue(student.getLastSubmit());
        }
        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /*
    * 首先上传excel表，然后以字符串形式返回excel表在服务端的路径
    *
    * */
    public static String uploadExcelFile(MultipartFile multipartFile) {
        String originname = multipartFile.getOriginalFilename();
        String folder = "exceltables";
        File targetFile = null;
        String baseurl = SiteConfig.BASE_URL;
        if (multipartFile != null) {
           /* FolderUtils.createFolder(baseurl,folder);*/
            //上传文件的路径
            try {
                targetFile = new File(
                        new File(ResourceUtils.getURL(baseurl).getPath())
                                .getAbsolutePath() + "/" + folder, originname);
                if(targetFile.exists()){

                    targetFile.delete();
                }

                targetFile.getParentFile().mkdirs();
                try {
                    if (targetFile.createNewFile()) {

                        multipartFile.transferTo(targetFile);
                        return targetFile.getAbsolutePath();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return "";
    }


   /* public static void main(String[] args) {
        ExcelUtils.importExcelToSQL("C:\\Users\\lenovo\\Desktop\\name.xlsx");
    }*/
}

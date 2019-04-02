package com.examine.controller;

import com.examine.common.util.ResponseResult;
import com.examine.service.ExamService;
import com.examine.service.PaperService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class PaperController {

    private final PaperService paperService;

    private final ExamService examService;

    @Autowired
    public PaperController(PaperService paperService, ExamService examService) {
        this.paperService = paperService;
        this.examService = examService;
    }

    @RequestMapping("/savePaper")
    @ResponseBody
    public ResponseResult savePaper(MultipartFile multipartFile, HttpSession session) throws FileNotFoundException {

        return paperService.SavePaperService(multipartFile, session);
    }

    @RequestMapping("/downloadPaper")
    public ResponseEntity<byte[]> downloadPaper(HttpServletRequest request, @RequestParam("fileName") String fileName) throws IOException {
        File downloadFilePath = new File(
                new File(ResourceUtils.getURL("src/main/resources/static").getPath())
                        .getAbsolutePath() + "/upload", fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(downloadFilePath), headers, HttpStatus.CREATED);
    }

}

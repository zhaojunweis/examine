package com.examine.controller;

import com.examine.common.util.ResponseResult;
import com.examine.service.SavePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Controller
public class SavePaperController {

    @Autowired
    public SavePaperService savePaperService;

    @RequestMapping("/savePaper")
    @ResponseBody
    public ResponseResult savePaper(MultipartFile multipartFile, HttpSession session) throws FileNotFoundException {
        return savePaperService.SavePaperService(multipartFile,session);
    }
}

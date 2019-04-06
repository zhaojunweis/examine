package com.examine.service;

import com.examine.common.util.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

public interface SavePaperService {
   ResponseResult SavePaperService(MultipartFile multipartFile, HttpSession session) throws FileNotFoundException;
}

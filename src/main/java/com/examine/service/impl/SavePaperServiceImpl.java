package com.examine.service.impl;

import com.examine.common.service.BaseService;
import com.examine.common.util.ResponseResult;
import com.examine.service.SavePaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class SavePaperServiceImpl extends BaseService implements SavePaperService {
    private static Logger logger = LoggerFactory.getLogger(SavePaperService.class);

    @Override
    public ResponseResult SavePaperService(MultipartFile multipartFile, HttpSession session) {
        if (multipartFile.getSize() > 0 && !multipartFile.isEmpty()) {
            String originalFileName = multipartFile.getOriginalFilename();
            File targetFilePath = null;
            try {
                targetFilePath = new File(
                        new File(ResourceUtils.getURL("src/main/resources/static").getPath())
                                .getAbsolutePath() + "/image", originalFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("upload file path" + targetFilePath.getAbsolutePath());
            if (!targetFilePath.exists()) {
                targetFilePath.getParentFile().mkdirs();
                try {
                    if (targetFilePath.createNewFile()) {
                        multipartFile.transferTo(targetFilePath);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ResponseResult.ok("upload file success");
        }
        return new ResponseResult("upload file failed");
    }
}

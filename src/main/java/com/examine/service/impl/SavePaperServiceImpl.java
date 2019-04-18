package com.examine.service.impl;

import com.examine.common.service.BaseService;
import com.examine.common.util.ResponseResult;
import com.examine.dao.ExamMapper;
import com.examine.service.SavePaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SavePaperServiceImpl extends BaseService implements SavePaperService {

    private final ExamMapper examMapper;

    private static Logger logger = LoggerFactory.getLogger(SavePaperService.class);

    @Autowired
    public SavePaperServiceImpl(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    /**
     * 教师上传试卷，保存试卷
     *
     * @param multipartFile
     * @param session
     * @return
     */
    @Override
    public ResponseResult SavePaperService(MultipartFile multipartFile, HttpSession session) {
        /*if (multipartFile.getSize() > 0 && !multipartFile.isEmpty()) {
            String originalFileName = multipartFile.getOriginalFilename();
            File targetFilePath = null;
            try {
                targetFilePath = new File(
                        new File(ResourceUtils.getURL("src/main/resources/static").getPath())
                                .getAbsolutePath() + "/upload", originalFileName);
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
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("examPaperUrl", targetFilePath.getAbsolutePath());
            paramMap.put("examName", (String) session.getAttribute("examName"));
            examMapper.uploadExamPaper(paramMap);
            return ResponseResult.ok("upload file success");
        }*/
        return new ResponseResult("upload file failed");
    }
}

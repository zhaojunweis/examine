package com.examine.service.impl;

import com.examine.common.service.BaseService;
import com.examine.common.util.ResponseResult;
import com.examine.dao.ExamMapper;
import com.examine.service.PaperService;
import com.examine.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Service
public class PaperServiceImpl extends BaseService implements PaperService {

    private final ExamMapper examMapper;

    @Autowired
    private SystemService systemService;

    private static Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    public PaperServiceImpl(ExamMapper examMapper) {

        this.examMapper = examMapper;
    }

    /**
     * 保存试卷
     *
     * @param multipartFile
     * @param session
     * @return
     */
    @Override
    public ResponseResult SavePaperService(MultipartFile multipartFile, HttpSession session) {
        //系统设置的文件最大上传容量
       /* long maxUploadSize = systemService.selectMaxUploadSize();
        //系统设置的文件最小上传容量
        long minUploadSize = systemService.selectMinUploadSize();*/
        //上传文件的大小
        long fileSize = multipartFile.getSize();

        if (fileSize > 0 && !multipartFile.isEmpty()) {
           /* if (fileSize >= minUploadSize && fileSize <= maxUploadSize) {
                String originalFileName = multipartFile.getOriginalFilename();
                String baseUrl = SiteConfig.BASE_URL;
                File targetFilePath = null;
                try {
                    //当进入的时候设置设置文件夹
                    //==========================创建文件夹========================================
                    TStudent student = (TStudent) session.getAttribute("student");
                    String teacher = (String) session.getAttribute("tName");
                    String folderName = "";
                    if (student != null) {
                        folderName = student.getsSno() + "_" + student.getsName();
                    }
                    if (teacher != null) {
                        folderName = teacher;
                    }
                    //==========================创建文件夹=================================
                    FolderUtils.createFolder(baseUrl, folderName);
                    targetFilePath = new File(
                            new File(ResourceUtils.getURL(baseUrl).getPath())
                                    .getAbsolutePath() + "/" + folderName, originalFileName);
                    //====================================================================//
                   *//* targetFilePath = new File(
                            new File(ResourceUtils.getURL("src/main/resources/static").getPath())
                                    .getAbsolutePath() + "/upload", originalFileName);*//*
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
                //当上传者为教师的话，设置设置文件路径
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("examPaperUrl", targetFilePath.getAbsolutePath());
                paramMap.put("examName", (String) session.getAttribute("examName"));
                examMapper.uploadExamPaper(paramMap);
                return ResponseResult.ok("upload file success");
            } else {
                return new ResponseResult("file is bigger or lower");
            }*/
        }
        return new ResponseResult("upload file failed");
    }
}

package com.examine.service.impl;

import com.examine.common.service.BaseService;
import com.examine.common.util.FolderUtils;
import com.examine.common.util.ResponseResult;
import com.examine.config.site.SiteConfig;
import com.examine.dao.ExamMapper;
import com.examine.dao.StudentMapper;
import com.examine.domain.TStudent;
import com.examine.service.PaperService;
import com.examine.service.SystemService;
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
public class PaperServiceImpl extends BaseService implements PaperService {

    private final ExamMapper examMapper;

    private final SystemService systemService;

    private final StudentMapper studentMapper;

    private static Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    public PaperServiceImpl(ExamMapper examMapper, SystemService systemService, StudentMapper studentMapper) {

        this.examMapper = examMapper;
        this.systemService = systemService;
        this.studentMapper = studentMapper;
    }

    /**
     * 保存试卷，教师上传和学生上传文件
     *
     * @param multipartFile
     * @param session
     * @return
     */
    @Override
    public ResponseResult SavePaperService(MultipartFile multipartFile, HttpSession session) {
        //系统设置的文件最大上传容量
        long maxUploadSize = systemService.selectMaxUploadSize();
        //系统设置的文件最小上传容量
        long minUploadSize = systemService.selectMinUploadSize();
        //上传文件的大小
        long fileSize = multipartFile.getSize();
        //上传的文件夹名称
        String folderName = "";
        //上传的文件名及后缀
        String originalFileName="";
        //上传判断，防止恶意上传
        if (fileSize > 0 && !multipartFile.isEmpty()) {
            //判断系统上传的最大和最小上限
            if (fileSize >= minUploadSize && fileSize <= maxUploadSize) {
                originalFileName = multipartFile.getOriginalFilename();
                String baseUrl = SiteConfig.BASE_URL;
                File targetFilePath = null;
                TStudent student = null;
                String teacher = "";
                try {
                    //从session中获取学生信息
                    student = (TStudent) session.getAttribute("student");
                    teacher = (String) session.getAttribute("tName");

                    //设置学生文件夹名称
                    if (student != null) {
                        folderName = student.getsSno() + "_" + student.getsName();
                    }
                    //设置教师文件夹名称
                    if (teacher != null) {
                        folderName = teacher;
                    }
                    //创建文件夹，其中判断了文件夹是否存在
                    FolderUtils.createFolder(baseUrl, folderName);
                    //上传文件的路径
                    targetFilePath = new File(
                            new File(ResourceUtils.getURL(baseUrl).getPath())
                                    .getAbsolutePath() + "/" + folderName, originalFileName);
//                   targetFilePath = new File(
//                            new File(ResourceUtils.getURL("src/main/resources/static").getPath())
//                                    .getAbsolutePath() + "/upload", originalFileName);
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
                //当上传者为教师的话，设置文件路径
                Map<String, String> paramMap = new HashMap<>();
                if (teacher != null) {
                    paramMap.put("examPaperUrl",folderName+"/"+originalFileName );
                    paramMap.put("examName", (String) session.getAttribute("examName"));
                    String str =  (String) session.getAttribute("examName");
                    examMapper.uploadExamPaper(paramMap);
                }
                //当上传者为学生的时候，设置文件路径
                if (student != null) {
                    paramMap.put("examPaperUrl", targetFilePath.getAbsolutePath());
                    paramMap.put("sSno", student.getsSno());
                    paramMap.put("filesize",String.valueOf(fileSize));
                    studentMapper.submitAnswer(paramMap);
                }
                return ResponseResult.ok("文件上传成功");
            } else {
                return new ResponseResult("文件大小不在规定范围内,请重新选择文件");
            }
        }
        return new ResponseResult("文件上传失败");
    }
}

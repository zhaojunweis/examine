package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TSystem;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class SystemController extends BaseController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping("/systemconfig")
    @ResponseBody
    public Map<String,Object> insertSystemConfigure(TSystem system){

        system.setId(12);
        system.setManualOpenTime("123");
        system.setMaxUploadSize(153);
        system.setMinUploadSize(23);
        system.setsCanDelete(1);
        system.setsExamTime("123");
        system.setsPageCount(12);
        boolean flag = systemService.insertSystemConfigure(system);
        if(!flag){
            resultMap.put("status",500);
            resultMap.put("message","insert system configure failed");
        }
        resultMap.put("status",200);
        resultMap.put("message","insert system configure success");
        return resultMap;
    }
}

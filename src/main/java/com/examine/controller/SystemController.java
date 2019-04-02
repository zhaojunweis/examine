package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TSystem;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    public Map<String,Object> updateSystemConfigure(TSystem system){

        boolean flag = systemService.updateSystemConfigure(system);
        if(!flag){
            resultMap.put("status",500);
            resultMap.put("message","insert system configure failed");
        }
        resultMap.put("status",200);
        resultMap.put("message","insert system configure success");
        return resultMap;
    }

    @RequestMapping("/selectSystemConfigure")
    @ResponseBody
    public TSystem selectSystemConfigure(){

        return systemService.selectSystemConfigure();
    }
}

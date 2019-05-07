package com.examine.controller;

import com.examine.common.controller.BaseController;
import com.examine.domain.TSystem;
import com.examine.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    /*
     * 系统配置界面初始化
     * */
    @RequestMapping("/admin_config")
    public ModelAndView EnterSystemConfig(){
        ModelAndView mv = new ModelAndView();
        TSystem tSystem = systemService.selectSystemConfigure();
        if(tSystem!=null){
            mv.addObject("systemconfig",tSystem);
        }
        mv.setViewName("admin_config");
        return mv;
    }
    /*@RequestMapping("/selectSystemConfigure")
    @ResponseBody
    public TSystem selectSystemConfigure(){

        return systemService.selectSystemConfigure();
    }*/
}

package com.examine.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/index")
    public String Test(){
        System.out.println("user");
        return "/index.html";
    }
}

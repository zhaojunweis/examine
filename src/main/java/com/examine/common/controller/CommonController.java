package com.examine.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
       session.invalidate();
       return new ModelAndView("redirect:/index");
    }
}

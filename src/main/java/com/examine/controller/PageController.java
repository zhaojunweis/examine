package com.examine.controller;

import com.examine.config.site.BundleReader;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("/")
    public ModelAndView index(HttpSession session){

        session.getServletContext().setAttribute("global_url",BundleReader.getGlobalUrl());
        return new ModelAndView("loginpage");
    }

}

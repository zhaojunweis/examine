package com.examine.common.controller;

import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class BaseController {
   protected Map<String,Object> resultMap = new LinkedHashMap<>();
}

package com.examine.controller;

import com.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

  @Autowired
  public StudentService studentService;

}

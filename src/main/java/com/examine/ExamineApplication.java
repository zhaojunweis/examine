package com.examine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com.examine.dao"})
public class ExamineApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExamineApplication.class, args);
    }
}

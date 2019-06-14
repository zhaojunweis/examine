package com.examine.config.servlet;


import com.examine.service.DaemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value = 1)
public class StartServlet implements ApplicationRunner {

    @Autowired
    private DaemonService daemonService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner" + daemonService);
        daemonService.scanMySQL();//开启扫描数据库
        daemonService.changeStatus();//修改扫描过程中的考试状态
    }
}

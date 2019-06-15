package com.examine.controller;

import com.examine.domain.TNotification;
import com.examine.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 新建消息
     *
     * @return
     */
    @RequestMapping("/insertMessage")
    @ResponseBody
    public boolean insertMessage() {
        TNotification notification = new TNotification();

        notification.setMessage("345");

        return notificationService.saveNotification(notification);
    }



}

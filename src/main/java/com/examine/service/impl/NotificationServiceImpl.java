package com.examine.service.impl;

import com.examine.dao.NotificationMapper;
import com.examine.domain.TNotification;
import com.examine.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 新建通知消息
     *
     * @param notification
     * @return
     */
    @Override
    public boolean saveNotification(TNotification notification) {
        boolean flag = false;
        Integer affectCount = notificationMapper.saveNotification(notification);

        if (affectCount == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询所有通知信息
     *
     * @return
     */
    @Override
    public List<TNotification> showAllNotification(Integer examId) {

        return notificationMapper.showAllNotification(examId);
    }
}

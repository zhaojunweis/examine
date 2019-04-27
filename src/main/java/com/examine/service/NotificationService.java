package com.examine.service;

import com.examine.domain.TNotification;

import java.util.List;

/**
 * @version 1.0
 * @Auther:wencaixu
 * @Date:2019/4/26
 * @Description:com.examine.service
 */

public interface NotificationService {

    boolean saveNotification(TNotification notification);

    List<TNotification> showAllNotification();
}

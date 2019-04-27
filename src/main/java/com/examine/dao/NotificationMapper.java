package com.examine.dao;

import com.examine.domain.TNotification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationMapper {

    /**
     * 新建通知
     *
     * @param notification
     * @return
     */
    Integer saveNotification(TNotification notification);

    /**
     * 查询所有的通知信息
     *
     * @return
     */
    List<TNotification> showAllNotification();

}

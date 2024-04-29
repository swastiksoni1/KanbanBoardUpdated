package com.bej.NotificationService.Service;

import com.bej.NotificationService.domain.Notification;

public interface INotificationService {

    Notification getUserNotification(String userId);
    Notification getBoardsNotification(String userId, String boardId);
    void saveNotification(Notification notification);
//    void saveNotification(JsonObject jsonObject);

}

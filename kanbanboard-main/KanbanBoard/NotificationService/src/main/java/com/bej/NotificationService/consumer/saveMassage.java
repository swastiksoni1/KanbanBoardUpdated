package com.bej.NotificationService.consumer;

import com.bej.NotificationService.Repository.INotificationRepository;
import com.bej.NotificationService.Service.INotificationService;
import com.bej.NotificationService.domain.BoardsNotifications;
import com.bej.NotificationService.domain.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class saveMassage {

    @Autowired
    INotificationService iNotificationService;

    @Autowired
    INotificationRepository iNotificationRepository;

    @Autowired
    public saveMassage(INotificationRepository iNotificationRepository,INotificationService iNotificationService) {
        this.iNotificationService = iNotificationService;
        this.iNotificationRepository = iNotificationRepository;
    }


    @RabbitListener(queues = "user-notification-queue")
    public void saveNotification(Notification notification) {
        System.out.println("notification message "+notification);
        String userId = notification.getUserId();
        if (userId != null) {
            // Check if a notification with the same userId already exists in the repository
            Notification existingNotification = iNotificationRepository.findByUserId(userId);

            if (existingNotification != null) {
                // Notification with the userId already exists, update it instead of creating a new one
                existingNotification.setNotificationMessage(notification.getNotificationMessage());
                if (notification.getBoardsNotificationsList() != null) {
                    existingNotification.setBoardsNotificationsList(notification.getBoardsNotificationsList());
                }

                // Save the updated notification
                iNotificationRepository.save(existingNotification);
            } else {
                // No existing notification found, create a new one and save it
                Notification newNotification = new Notification();
                newNotification.setUserId(notification.getUserId());
                newNotification.setNotificationMessage(notification.getNotificationMessage());
                newNotification.setBoardsNotificationsList(notification.getBoardsNotificationsList());

                iNotificationRepository.save(newNotification);
            }
        }
    }
}


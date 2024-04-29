package com.bej.NotificationService.Service;

import com.bej.NotificationService.Repository.INotificationRepository;
import com.bej.NotificationService.domain.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private INotificationRepository INotificationRepository;

    @Autowired
    public NotificationServiceImpl(INotificationRepository INotificationRepository) {
        this.INotificationRepository = INotificationRepository;
    }

    @Override
    public Notification getUserNotification(String userId) {
        return null;
    }

    @Override
    public Notification getBoardsNotification(String userId, String boardId) {
        return null;
    }


//    @RabbitListener(queues = "user-notification-queue")
    @Override
    public void saveNotification(Notification notification) {

        System.out.println(notification);
    }
//        String userId=notification.getUserId();
//        if(INotificationRepository.findById(userId).isEmpty()){
//            Notification notification1 = new Notification();
//
//            notification1.setUserId(notification.getUserId());
//            notification1.setNotificationMessage(notification.getNotificationMessage());
//            INotificationRepository.save(notification1);
//        }
//        Notification notification1= INotificationRepository.findById(userId).get();
//        notification1.setUserId(notification.getUserId());
//        notification1.setNotificationMessage(notification.getNotificationMessage());
//        INotificationRepository.save(notification);
//
//    }
}

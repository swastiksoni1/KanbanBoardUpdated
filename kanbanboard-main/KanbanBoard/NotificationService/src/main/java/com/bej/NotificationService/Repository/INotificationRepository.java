package com.bej.NotificationService.Repository;

import com.bej.NotificationService.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends MongoRepository<Notification,String> {
    Notification findByUserId(String userId);
}

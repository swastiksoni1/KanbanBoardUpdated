package com.bej.kanbanService.config;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Notification {

    @Id
    private String userId;
    private List<String> notificationMessage;
    private boolean notificationMessageStatus = false;
    private List<BoardsNotifications> boardsNotificationsList;

}

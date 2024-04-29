package com.bej.NotificationService.domain;


import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document
public class BoardsNotifications {
    @Id
    private String boardId;
    private List<String> boardMessage;
    private boolean boardMessageStatus = false;

}

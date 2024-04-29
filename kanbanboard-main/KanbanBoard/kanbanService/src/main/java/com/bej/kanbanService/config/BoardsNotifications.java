package com.bej.kanbanService.config;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class BoardsNotifications {
    @Id
    private String boardId;
    private List<String> boardMessage;
    private boolean boardMessageStatus = false;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public List<String> getBoardMessage() {
        return boardMessage;
    }

    public void setBoardMessage(List<String> boardMessage) {
        this.boardMessage = boardMessage;
    }

    public boolean isBoardMessageStatus() {
        return boardMessageStatus;
    }

    public void setBoardMessageStatus(boolean boardMessageStatus) {
        this.boardMessageStatus = boardMessageStatus;
    }
}

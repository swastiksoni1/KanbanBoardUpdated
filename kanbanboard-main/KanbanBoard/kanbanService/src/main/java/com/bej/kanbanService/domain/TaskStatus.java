package com.bej.kanbanService.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class TaskStatus {
    @Id
    private long id;
    private String description;
    private String value;

}

package com.bej.kanbanService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private String taskId;
    private String taskName;
    private String taskDescription;
    private String dueDate;
    private String priority;
    private String taskStatus;

}

package com.bej.kanbanService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

@Document
public class User {

    @Id
    private String userId;
    private String password;
    private String userName;
    private List<KanbanBoard> kanbanBoardList; // project

}

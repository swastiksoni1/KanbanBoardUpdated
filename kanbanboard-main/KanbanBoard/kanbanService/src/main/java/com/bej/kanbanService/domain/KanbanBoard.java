package com.bej.kanbanService.domain;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KanbanBoard {
    @Id
    private String kanbanBoardId;
    private String boardName;
    private List<TeamMember> teamMemberList;

}


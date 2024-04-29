package com.bej.kanbanService.service;


import com.bej.kanbanService.domain.KanbanBoard;
import com.bej.kanbanService.domain.TeamMember;
import com.bej.kanbanService.domain.User;
import com.bej.kanbanService.exception.*;

import java.util.List;

public interface IKanbanBoardService {
    User saveBoard(KanbanBoard kanbanBoard, String userId) throws UserNotFoundException, LimitReachedException, TaskAlreadyExistException, BoardAlreadyExistException;
    User deleteBoard(String userId, String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException;
    List<KanbanBoard> getAllBoards(String userId) throws UserNotFoundException, NoItemsInListException;

}

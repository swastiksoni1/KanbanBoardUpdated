package com.bej.kanbanService.service;

import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;

import java.util.List;

public interface IUserKanbanService {
    User saveUser (User user) throws UserAlredyExistException;
    User saveUserTaskToList (String boardId, String memberEmailId, Task task, String userId) throws UserNotFoundException, LimitReachedException, TaskAlreadyExistException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException;
    User updateUserTaskFromGivenList (String userId, String boardId, String  memberEmailId, String taskId, Task task) throws UserNotFoundException, TaskNotFoundException, LimitReachedException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException;
    User deleteTaskFromList (String boardId, String memberEmailId,String userId, String taskId) throws UserNotFoundException, TaskNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException;
    List<Task> getAllUserTaskFromList (String userId, String memberEmailId,String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException;

}

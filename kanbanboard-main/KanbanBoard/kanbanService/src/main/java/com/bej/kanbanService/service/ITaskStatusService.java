package com.bej.kanbanService.service;

import com.bej.kanbanService.domain.Task;
import com.bej.kanbanService.domain.TaskStatus;
import com.bej.kanbanService.domain.User;
import com.bej.kanbanService.exception.*;

public interface ITaskStatusService {
    TaskStatus createTaskStatus(TaskStatus taskStatus);
    User updateTaskStatus(String userId,  String boardId, String memberEmailId, String taskId, String taskStatus) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException, TaskNotFoundException, LimitReachedException, InvalidStatusChangeException;

}

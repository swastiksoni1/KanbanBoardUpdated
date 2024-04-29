package com.bej.kanbanService.service;

import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.repository.TaskStatusRepository;
import com.bej.kanbanService.repository.UserRepository;
import com.bej.kanbanService.utils.KANBANUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class TaskStatusServiceImpl implements ITaskStatusService {


    private static final String IN_PROGRESS="IN_PROGRESS";
    private UserRepository userRepository;
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskStatusServiceImpl(UserRepository userRepository,TaskStatusRepository taskStatusRepository) {
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
    }


    @Override
    public TaskStatus createTaskStatus(TaskStatus taskStatus) {
        return taskStatusRepository.save(taskStatus);
    }

//    @Override
//    public User updateTaskStatus( String taskStatus,  String boardId, String memberEmailId, String userId, String taskId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException, TaskNotFoundException, LimitReachedException, InvalidStatusChangeException {
//
//        User user = KANBANUtils.getUserById(userRepository, userId);
//
//        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);
//
//        List<TeamMember> teamMembers = foundBoard.getTeamMemberList();
//
//        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);
//
//        Task taskFound = KANBANUtils.getTask(teamMemberfound, taskId);
//
//        List<Task> taskList = teamMemberfound.getTaskList();
//
//                TaskStatus taskStatus1=getTaskStatusByValue(taskStatus);
//
//        // Check if the taskStatus is "In Progress"
////        if (taskStatus1.getValue().toUpperCase() == "IN_PROGRESS") {
////
////            if (taskFound.getPriority().toUpperCase() == "HIGH") {
////                // Restrict status change from "In Progress" to "Todo" or "Review"
////                if (taskStatus1.getValue().toUpperCase() == "REVIEW" || taskStatus1.getValue().toUpperCase() == "COMPLETE") {
////                    // Perform the status change to "Review" or "Complete"
////                    taskFound.setTaskStatus(taskStatus1.getValue());
////                } else {
////                    throw new InvalidStatusChangeException();
////                }
////            }
//        if (taskFound.getTaskStatus().toUpperCase() == "IN_PROGRESS") {
//
//            if (taskFound.getPriority().toUpperCase() == "HIGH") {
//                // Restrict status change from "In Progress" to "Todo" or "Review"
//                if (taskStatus1.getValue().toUpperCase() == "REVIEW" || taskStatus1.getValue().toUpperCase() == "COMPLETE") {
//                    // Perform the status change to "Review" or "Complete"
//                    taskFound.setTaskStatus(taskStatus1.getValue());
//                } else {
//                    throw new InvalidStatusChangeException();
//                }
//            }
//
//            // Count the number of tasks with the "In Progress" status
//            long inProgressTaskCount = taskList.stream()
//                    .filter(task -> task.getTaskStatus() == "IN_PROGRESS")
//                    .count();
//            if (inProgressTaskCount >= 2) {
//                throw new LimitReachedException();
//            }
//
//        }
//                //taskFound.setTaskStatus(taskStatus1.getValue());
////                taskList.set(taskFound);
////                teamMemberfound.setTaskList(taskList);
//
//
//            return userRepository.save(user);
//        }
@Override
public User updateTaskStatus( String userId,  String boardId, String memberEmailId, String taskId, String taskStatus) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException, TaskNotFoundException, LimitReachedException, InvalidStatusChangeException {
    User user = KANBANUtils.getUserById(userRepository, userId);
    KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user, boardId);
    List<TeamMember> teamMembers = foundBoard.getTeamMemberList();
    TeamMember teamMemberfound = KANBANUtils.getTeamMember(foundBoard, memberEmailId);
    Task taskFound = KANBANUtils.getTask(teamMemberfound, taskId);
    List<Task> taskList = teamMemberfound.getTaskList();
    TaskStatus taskStatus1 = getTaskStatusByValue(taskStatus);

// Check if the taskStatus is "In Progress"
//    if (taskFound.getTaskStatus().equalsIgnoreCase("IN_PROGRESS")) {
//        if (taskFound.getPriority().equalsIgnoreCase("HIGH")) {
//            if (taskStatus1.getValue().equalsIgnoreCase("REVIEW") || taskStatus1.getValue().equalsIgnoreCase("COMPLETE")) {
//                taskFound.setTaskStatus(taskStatus1.getValue());
//            } else {
//                throw new InvalidStatusChangeException();
//            }
//        }
//
//        // Count the number of tasks with the "In Progress" status
//        long inProgressTaskCount = taskList.stream()
//                .filter(task -> task.getTaskStatus().equalsIgnoreCase("IN_PROGRESS"))
//                .count();
//        if (inProgressTaskCount >= 2) {
//            throw new LimitReachedException();
//        }
//    }
    taskFound.setTaskStatus(taskStatus1.getValue());

    return userRepository.save(user);

}
    private TaskStatus getTaskStatusByValue(String taskStatus) {
        TaskStatus newTaskStatus=taskStatusRepository.findByValue(taskStatus).orElse(null);
        return newTaskStatus;
    }
}


package com.bej.kanbanService.service;

import com.bej.kanbanService.config.Notification;
import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.proxy.UserProxy;
import com.bej.kanbanService.repository.TaskStatusRepository;
import com.bej.kanbanService.repository.UserRepository;
import com.bej.kanbanService.utils.KANBANUtils;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserKanbanServiceImpl implements IUserKanbanService {

    private UserRepository userRepository;
    private TaskStatusRepository taskStatusRepository;
    private UserProxy userProxy;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    private static final String TODO = "TO_DO";
    private static final String IN_PROGRESS = "IN_PROGRESS";


    @Autowired
    public UserKanbanServiceImpl(DirectExchange directExchange, RabbitTemplate rabbitTemplate,UserRepository userRepository, UserProxy userProxy, TaskStatusRepository taskStatusRepository) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlredyExistException {
        if (userRepository.findById(user.getUserId()).isPresent()) {
            throw new UserAlredyExistException();
        }
        userProxy.saveUser(user);
        List<String> messageString = new ArrayList<>();
        String message = "Welcome "+user.getUserName();
        messageString.add(message);
        Notification notification = new Notification();
        notification.setUserId(user.getUserId());
        notification.setNotificationMessage(messageString);
        rabbitTemplate.convertAndSend("user-notification-exchange","user-routing",notification);
        return userRepository.save(user);

    }

    @Override
    public User saveUserTaskToList(String boardId, String memberEmailId, Task task, String userId) throws UserNotFoundException, LimitReachedException, TaskAlreadyExistException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {
        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);



        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);

            if (teamMemberfound.getTaskList() == null) {
                TaskStatus taskStatus=getTaskStatusByValue(TODO);
                teamMemberfound.setTaskList(Arrays.asList(task));
                task.setTaskStatus(taskStatus.getValue());
            } else {
                List<Task> taskList = teamMemberfound.getTaskList();

                if (taskList.size()>=4){
                    throw new LimitReachedException();
                }
                // Check if the task already exists in the taskList
                boolean taskAlreadyExists = taskList.stream()
                        .anyMatch(existingTask -> existingTask.getTaskId().equals(task.getTaskId()));

                if (taskAlreadyExists) {
                    throw new TaskAlreadyExistException();
                }

                TaskStatus taskStatus=getTaskStatusByValue(TODO);
                task.setTaskStatus(taskStatus.getValue());
                taskList.add(task);
                teamMemberfound.setTaskList(taskList);

            }


        return  userRepository.save(user);

    }




    @Override
    public User updateUserTaskFromGivenList(String userId, String boardId, String  memberEmailId, String taskId, Task task) throws UserNotFoundException, TaskNotFoundException, LimitReachedException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {
        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        List<TeamMember> teamMembers = foundBoard.getTeamMemberList();

        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);



        Task taskFound = KANBANUtils.getTask(teamMemberfound, taskId);


        taskFound.setTaskName(task.getTaskName());
        taskFound.setTaskDescription(task.getTaskDescription());
        taskFound.setDueDate(task.getDueDate());
        taskFound.setPriority(task.getPriority());

        List<Task> taskList = teamMemberfound.getTaskList();

        teamMemberfound.setTaskList(taskList);

        return  userRepository.save(user);
    }

    @Override
    public User deleteTaskFromList(String boardId, String memberEmailId,String userId, String taskId) throws UserNotFoundException, TaskNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        List<TeamMember> teamMembers = foundBoard.getTeamMemberList();

        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);

        Task taskFound = KANBANUtils.getTask(teamMemberfound, taskId);
        List<Task> taskList = teamMemberfound.getTaskList();
        taskList.remove(taskFound);


//        teamMemberfound.setTaskList(taskList);
        return  userRepository.save(user);
    }

    @Override
    public List<Task> getAllUserTaskFromList(String userId, String memberEmailId,String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);

        List<Task> taskList = teamMemberfound.getTaskList();

        return taskList;
    }

    private TaskStatus getTaskStatusByValue(String taskStatus) {
        TaskStatus newTaskStatus=taskStatusRepository.findByValue(taskStatus).orElse(null);
        return newTaskStatus;
    }

//    private static void validateTaskList(String taskStatus, List<Task> list) throws LimitReachedException {
//        if(taskStatus!=IN_PROGRESS){
//            return;
//        }
//
//        List<Task> inprogresstasks = list.stream().filter(task1 -> {
//            if (task1.getTaskStatus() != null) {
//                return task1.getTaskStatus().getValue() == taskStatus;
//            }
//            return false;
//        }).collect(Collectors.toList());
//
//        if(inprogresstasks.size()>=6){
//            throw new LimitReachedException();
//        }
//    }
}


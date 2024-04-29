package com.bej.kanbanService.controller;

import com.bej.kanbanService.domain.Task;
import com.bej.kanbanService.domain.TaskStatus;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.service.ITaskStatusService;
import com.bej.kanbanService.service.IUserKanbanService;
import com.mongodb.lang.Nullable;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private ITaskStatusService iTaskStatusService;

    private IUserKanbanService userKanbanService;
    private ITaskStatusService taskStatusService;
    private ResponseEntity responseEntity;

    @Autowired
    public TaskController(IUserKanbanService userKanbanService,ITaskStatusService taskStatusService) {
        this.userKanbanService = userKanbanService;
        this.taskStatusService = taskStatusService;
    }

    //assign task status
    @PostMapping("/taskStatus")
    ResponseEntity<TaskStatus> createTaskStatus(@RequestBody TaskStatus taskStatus){
       TaskStatus status= iTaskStatusService.createTaskStatus(taskStatus);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    // add a task to a specific user
    @PostMapping("/user/saveTask/{boardId}/{memberEmailId}")
    public ResponseEntity<?> saveUserTaskToList(@PathVariable String boardId, @PathVariable String memberEmailId, @RequestBody Task task, HttpServletRequest request) throws UserNotFoundException, TeamMemberNotFoundException, LimitReachedException, TaskAlreadyExistException, NoItemsInListException, BoardNotFoundException {

        try {
            String userId = getuseridfromclaims(request);
            responseEntity = new ResponseEntity<>(userKanbanService.saveUserTaskToList(boardId, memberEmailId, task, userId), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TeamMemberNotFoundException e) {
            throw new TeamMemberNotFoundException();
        } catch (LimitReachedException e) {
            throw new LimitReachedException();
        } catch (TaskAlreadyExistException e) {
            throw new TaskAlreadyExistException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }
        return responseEntity;
    }



    // list all task of a specific user,
    @GetMapping("/user/getAllTask/{boardId}/{memberEmailId}")
    public ResponseEntity<?> getAllTaskFromList(@PathVariable String boardId, @PathVariable String memberEmailId, HttpServletRequest request) throws UserNotFoundException, TeamMemberNotFoundException, NoItemsInListException, BoardNotFoundException {
        try {
            String userId = getuseridfromclaims(request);
            responseEntity = new ResponseEntity<>(userKanbanService.getAllUserTaskFromList(userId,memberEmailId, boardId ), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TeamMemberNotFoundException e) {
            throw new TeamMemberNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }
        return responseEntity;
    }


    // delete task of a specific user,
    @DeleteMapping("/user/deleteTask/{boardId}/{memberEmailId}/{taskId}")
    public ResponseEntity<?> deleteTaskFromList(@PathVariable String boardId, @PathVariable String memberEmailId,@PathVariable String taskId, HttpServletRequest request) throws TaskNotFoundException, UserNotFoundException, TeamMemberNotFoundException, NoItemsInListException, BoardNotFoundException {
        String userId = getuseridfromclaims(request);
        try {
            responseEntity = new ResponseEntity<>(userKanbanService.deleteTaskFromList(boardId, memberEmailId, userId, taskId), HttpStatus.OK);
        } catch (TaskNotFoundException m){
            throw new TaskNotFoundException();
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TeamMemberNotFoundException e) {
            throw new TeamMemberNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }
        return responseEntity;
    }



    // update a task based on user id and assign it to todo , extract user id from claims
    @PutMapping("/user/updateTask/{boardId}/{memberEmailId}/{taskId}")
    public ResponseEntity<?> updateUserTask (@PathVariable String boardId, @PathVariable String memberEmailId,@PathVariable String taskId,@RequestBody Task task, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException, LimitReachedException, TeamMemberNotFoundException, NoItemsInListException, BoardNotFoundException,InvalidStatusChangeException{

        String userId = getuseridfromclaims(request);
        try {

            return new ResponseEntity<>(userKanbanService.updateUserTaskFromGivenList(userId, boardId, memberEmailId, taskId,  task ), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException();
        } catch (TeamMemberNotFoundException e) {
            throw new TeamMemberNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }
    }

    /// method for short
    static String getuseridfromclaims(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        return userId;
    }

}

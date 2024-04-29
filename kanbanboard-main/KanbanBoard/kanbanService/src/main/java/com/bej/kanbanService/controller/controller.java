package com.bej.kanbanService.controller;

import com.bej.kanbanService.domain.Task;
import com.bej.kanbanService.domain.TaskStatus;
import com.bej.kanbanService.domain.User;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.service.ITaskStatusService;
import com.bej.kanbanService.service.IUserKanbanService;
import com.mongodb.lang.Nullable;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class controller {

    private IUserKanbanService userKanbanService;
    private ITaskStatusService taskStatusService;
    private ResponseEntity responseEntity;

@Autowired
    public controller(IUserKanbanService userKanbanService,ITaskStatusService taskStatusService) {
        this.userKanbanService = userKanbanService;
        this.taskStatusService = taskStatusService;
    }


    // Register a new user and save to database (Mongo)
    @PostMapping("/registerUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlredyExistException {
        try {
            responseEntity = new ResponseEntity<>(userKanbanService.saveUser(user), HttpStatus.CREATED);
        } catch (UserAlredyExistException e) {
            throw new UserAlredyExistException();
        }
        return responseEntity;
    }




    @PutMapping("/user/task/setTaskStatus/{boardId}/{memberEmailId}/{taskId}")
    public ResponseEntity<?> updateUserTaskStatus ( @PathVariable String boardId, @PathVariable String memberEmailId, @PathVariable String taskId, HttpServletRequest request,@RequestParam String taskStatus) throws UserNotFoundException, TaskNotFoundException, TeamMemberNotFoundException, NoItemsInListException, BoardNotFoundException {

        try {
            String userId = getuseridfromclaims(request);
            return new ResponseEntity<>(taskStatusService.updateTaskStatus(userId, boardId, memberEmailId,  taskId, taskStatus  ), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            // Handle the UserNotFoundException
            throw new UserNotFoundException();
        } catch (TaskNotFoundException e) {
            // Handle the TaskNotFoundException
            throw new TaskNotFoundException();
        }catch (TeamMemberNotFoundException e) {
            // Handle the TeamMemberNotFoundException
            throw new TeamMemberNotFoundException();
        } catch (NoItemsInListException e) {
            // Handle the NoItemsInListException
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            // Handle the BoardNotFoundException
            throw new BoardNotFoundException();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /// method for short
    static String getuseridfromclaims(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        return userId;
    }



}

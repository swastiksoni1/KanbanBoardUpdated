package com.bej.kanbanService.controller;
import com.bej.kanbanService.domain.KanbanBoard;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.service.IKanbanBoardService;
import com.bej.kanbanService.service.IUserKanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.bej.kanbanService.controller.controller.getuseridfromclaims;


@RestController
@RequestMapping("/api/v1")
public class kanbanBoardController {

    private IKanbanBoardService kanbanBoardService;
    private ResponseEntity responseEntity;

    @Autowired
    public kanbanBoardController(IKanbanBoardService kanbanBoardService) {
        this.kanbanBoardService = kanbanBoardService;
    }

    @PostMapping("/user/saveBoard")
    public ResponseEntity<?> saveBoards(@RequestBody KanbanBoard kanbanBoard, HttpServletRequest request) throws UserNotFoundException, TaskAlreadyExistException, LimitReachedException, BoardAlreadyExistException {

        String userId = getuseridfromclaims(request);
        try {
            responseEntity = new ResponseEntity<>(kanbanBoardService.saveBoard(kanbanBoard, userId), HttpStatus.CREATED);
        } catch (LimitReachedException e) {
            throw new LimitReachedException();
        } catch (TaskAlreadyExistException e) {
            throw new TaskAlreadyExistException();
        } catch (BoardAlreadyExistException e) {
            throw new BoardAlreadyExistException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/deleteBoard/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable String boardId, HttpServletRequest request) throws  UserNotFoundException, NoItemsInListException, BoardNotFoundException {
        String userId = getuseridfromclaims(request);
        try {
            responseEntity = new ResponseEntity<>(kanbanBoardService.deleteBoard(userId, boardId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }

        return responseEntity;
    }

    @GetMapping("/user/getAllBoards")
    public ResponseEntity<?> getAllBoards(HttpServletRequest request) throws UserNotFoundException, NoItemsInListException {

            String userId = getuseridfromclaims(request);
        System.out.println("hello there this is get all boards "+request);
        try {
            responseEntity = new ResponseEntity<>(kanbanBoardService.getAllBoards(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        }

        return responseEntity;
    }
}

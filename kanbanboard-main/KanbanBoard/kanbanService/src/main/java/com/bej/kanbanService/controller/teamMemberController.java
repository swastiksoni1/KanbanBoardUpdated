package com.bej.kanbanService.controller;

import com.bej.kanbanService.domain.Task;
import com.bej.kanbanService.domain.TeamMember;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.service.ITeamMemberService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.bej.kanbanService.controller.controller.getuseridfromclaims;

@RestController
@RequestMapping("/api/v1")
public class teamMemberController {

    private ITeamMemberService teamMemberService;
    private ResponseEntity responseEntity;

    @Autowired
    public teamMemberController(ITeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @PostMapping("/user/saveMember/{boardId}")
    public ResponseEntity<?> addTeamMember(@PathVariable String boardId, @RequestBody TeamMember teamMember, HttpServletRequest request) throws UserNotFoundException, UserAlredyExistException, NoItemsInListException, BoardNotFoundException, TeamMemberAlreadyExistException, LimitReachedException {


        String userId = getuseridfromclaims(request);
        try {
            responseEntity = new ResponseEntity<>(teamMemberService.addTeamMember(teamMember, boardId, userId), HttpStatus.CREATED);
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        } catch (TeamMemberAlreadyExistException e) {
            throw new TeamMemberAlreadyExistException();
        } catch (LimitReachedException e) {
            throw new LimitReachedException();
        }

        return responseEntity;
    }

    @DeleteMapping("/user/deleteMember/{boardId}/{memberEmailId}")
    public ResponseEntity<?> deleteTeamMember( @PathVariable String boardId, @PathVariable String memberEmailId, HttpServletRequest request) throws TaskNotFoundException, UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {
        String userId = getuseridfromclaims(request);

        try {
            responseEntity = new ResponseEntity<>(teamMemberService.deleteTeamMember(userId, boardId, memberEmailId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        } catch (TeamMemberNotFoundException e) {
            throw new TeamMemberNotFoundException();
        }


        return responseEntity;
    }


    @GetMapping("/user/getAllMember/{boardId}")
    public ResponseEntity<?> getAllTeamMembers(@PathVariable String boardId, HttpServletRequest request) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException {


        try {
            String userId = getuseridfromclaims(request);
            responseEntity = new ResponseEntity<>(teamMemberService.getAllTeamMembers(userId, boardId), HttpStatus.OK);
        } catch (NoItemsInListException e) {
            throw new NoItemsInListException();
        } catch (BoardNotFoundException e) {
            throw new BoardNotFoundException();
        }

        return responseEntity;
    }

    static String getuseridfromclaims(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        return userId;
    }


}

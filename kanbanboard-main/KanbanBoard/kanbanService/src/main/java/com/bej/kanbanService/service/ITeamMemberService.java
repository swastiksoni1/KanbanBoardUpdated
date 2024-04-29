package com.bej.kanbanService.service;

import com.bej.kanbanService.domain.KanbanBoard;
import com.bej.kanbanService.domain.TeamMember;
import com.bej.kanbanService.domain.User;
import com.bej.kanbanService.exception.*;

import java.util.List;

public interface ITeamMemberService {
    User addTeamMember (TeamMember teamMember, String boardId, String UserId) throws UserAlredyExistException, UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberAlreadyExistException, LimitReachedException;
    User deleteTeamMember ( String userId,String boardId, String memberEmailId ) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException;
    List<TeamMember> getAllTeamMembers(String userId, String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException;
}

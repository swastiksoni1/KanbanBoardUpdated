package com.bej.kanbanService.service;

import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.repository.KanbanBoardRepository;
import com.bej.kanbanService.repository.UserRepository;
import com.bej.kanbanService.utils.KANBANUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.bej.kanbanService.utils.KANBANUtils.getKanbanBoard;

@Service
public class TeamMemberServiceImpl implements ITeamMemberService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public TeamMemberServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addTeamMember(TeamMember teamMember, String boardId, String userId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberAlreadyExistException, LimitReachedException {
        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);



        if (foundBoard.getTeamMemberList() == null) {
            foundBoard.setTeamMemberList(Arrays.asList(teamMember));
        }else {
            List<TeamMember> teamMembers = foundBoard.getTeamMemberList();

            if (foundBoard.getTeamMemberList() != null) {
                if (teamMembers.size() >= 4) {
                    throw new LimitReachedException();
                }

            }

            // Check if the team member already exists in the list
            boolean teamMemberAlreadyExists = teamMembers.stream()
                    .anyMatch(existingTeamMember -> existingTeamMember.getMemberEmailId().equals(teamMember.getMemberEmailId()));

            if (teamMemberAlreadyExists) {
                throw new TeamMemberAlreadyExistException();
            }


            teamMembers.add(teamMember);
            foundBoard.setTeamMemberList(teamMembers);
        }
        return userRepository.save(user);
    }


    @Override
    public User deleteTeamMember(String userId, String boardId, String memberEmailId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException, TeamMemberNotFoundException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        List<TeamMember> teamMembers = foundBoard.getTeamMemberList();

        TeamMember teamMemberfound =KANBANUtils.getTeamMember(foundBoard, memberEmailId);

        teamMembers.remove(teamMemberfound);
        foundBoard.setTeamMemberList(teamMembers);

        return userRepository.save(user);
    }

    @Override
    public List<TeamMember> getAllTeamMembers(String userId, String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException {
        User user = KANBANUtils.getUserById(userRepository, userId);

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        List<TeamMember> teamMembers = foundBoard.getTeamMemberList();

        return teamMembers;
    }

}





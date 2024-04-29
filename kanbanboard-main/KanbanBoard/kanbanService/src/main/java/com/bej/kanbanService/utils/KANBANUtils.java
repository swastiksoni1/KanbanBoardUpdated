package com.bej.kanbanService.utils;

import com.bej.kanbanService.domain.KanbanBoard;
import com.bej.kanbanService.domain.Task;
import com.bej.kanbanService.domain.TeamMember;
import com.bej.kanbanService.domain.User;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

public class KANBANUtils {


    public static User getUserById(UserRepository userRepository, String userId) throws UserNotFoundException {
        try {
            return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        } catch (UserNotFoundException e) {
            throw e; // Re-throw the UserNotFoundException if it occurs.
        } catch (Exception e) {
            // Handle any other exceptions that might occur during database operations.
            // You can log the exception or perform other error handling here.
            throw new RuntimeException("An error occurred while retrieving the user.", e);
        }
    }


    public static KanbanBoard getKanbanBoard(User user, String boardId) throws NoItemsInListException, BoardNotFoundException {

        List<KanbanBoard> kanbanBoardList = user.getKanbanBoardList();

        if (kanbanBoardList.isEmpty()) {
            throw new NoItemsInListException();
        }

        KanbanBoard foundBoard = null;
        for (KanbanBoard kanbanBoard : kanbanBoardList) {
            if (kanbanBoard.getKanbanBoardId().equals(boardId)) {
                foundBoard = kanbanBoard;
                break; // Exit the loop once the board is found
            }
        }

        if (foundBoard == null) {
            throw new BoardNotFoundException();
        }

        return foundBoard;

    }


    public static TeamMember getTeamMember(KanbanBoard foundBoard, String memberEmailId) throws NoItemsInListException, TeamMemberNotFoundException {

        TeamMember teamMemberfound = null;
        if (foundBoard.getTeamMemberList() == null) {
            throw new NoItemsInListException();
        } else {
            List<TeamMember> teamMemberList = foundBoard.getTeamMemberList();

            for (TeamMember teamMember : teamMemberList) {
                if (teamMember.getMemberEmailId().equals(memberEmailId)) {
                    teamMemberfound = teamMember;
                    break; // Exit the loop once the board is found
                }
            }
            if (teamMemberfound == null) {
               throw new TeamMemberNotFoundException();
            }

        }
        return teamMemberfound;
    }


    public static Task getTask(TeamMember teamMemberfound, String taskId) throws NoItemsInListException, TaskNotFoundException {

        Task taskFound = null;

        if (teamMemberfound.getTaskList() == null) {
            throw new NoItemsInListException();
        } else {
            List<Task> taskList = teamMemberfound.getTaskList();

            for (Task task : taskList) {
                if (task.getTaskId().equals(taskId)) {
                    taskFound = task;
                    break; // Exit the loop once the board is found
                }
            }
            if (taskFound == null) {
                throw new TaskNotFoundException();
            }

        }
        return taskFound;
    }
}


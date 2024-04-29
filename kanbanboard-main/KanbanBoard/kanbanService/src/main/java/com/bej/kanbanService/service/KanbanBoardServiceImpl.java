package com.bej.kanbanService.service;


import com.bej.kanbanService.config.BoardsNotifications;
import com.bej.kanbanService.config.Notification;
import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.repository.UserRepository;
import com.bej.kanbanService.utils.KANBANUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KanbanBoardServiceImpl implements IKanbanBoardService{

    @Autowired
    private UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public KanbanBoardServiceImpl(UserRepository userRepository,RabbitTemplate rabbitTemplate ) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public User saveBoard(KanbanBoard kanbanBoard , String userId) throws UserNotFoundException, LimitReachedException, BoardAlreadyExistException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        if (user.getKanbanBoardList() == null) {
            user.setKanbanBoardList(Arrays.asList(kanbanBoard));
        } else {
            List<KanbanBoard> kanbanBoards = user.getKanbanBoardList();
            if (kanbanBoards.size()>=3){
                throw new LimitReachedException();
            }

            // Check if the task board exists in the taskList
            boolean boardAlreadyExists = kanbanBoards.stream()
                    .anyMatch(existingTask -> existingTask.getKanbanBoardId().equals(kanbanBoard.getKanbanBoardId()));

            if (boardAlreadyExists) {
                throw new BoardAlreadyExistException();
            }
            kanbanBoards.add(kanbanBoard);
            user.setKanbanBoardList(kanbanBoards);
        }
        List<String> messageString = new ArrayList<>();
        String message = kanbanBoard.getBoardName()+" created by "+user.getUserName();
        messageString.add(message);
        Notification notification = new Notification();
        notification.setUserId(user.getUserId());
        notification.setNotificationMessage(messageString);
        List<BoardsNotifications> boardList = new ArrayList<>();
        BoardsNotifications boardNotification = new BoardsNotifications();
        boardNotification.setBoardId(kanbanBoard.getKanbanBoardId());
        boardList.add(boardNotification);
        notification.setBoardsNotificationsList(boardList);
        rabbitTemplate.convertAndSend("user-notification-exchange","user-routing",notification);
        return userRepository.save(user);

    }


    @Override
    public User deleteBoard(String userId, String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        List<KanbanBoard> kanbanBoardList = user.getKanbanBoardList();

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        if (foundBoard != null) {
            kanbanBoardList.remove(foundBoard);
            user.setKanbanBoardList(kanbanBoardList);
        }

        return userRepository.save(user);
    }

    @Override
    public List<KanbanBoard> getAllBoards(String userId) throws UserNotFoundException, NoItemsInListException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        List<KanbanBoard> kanbanBoardList = user.getKanbanBoardList();

        if (kanbanBoardList.isEmpty()) {
            throw new NoItemsInListException();
        }

        return kanbanBoardList;
    }


}

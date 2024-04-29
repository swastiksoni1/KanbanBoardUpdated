package com.bej.kanbanService.repository;

import com.bej.kanbanService.domain.KanbanBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface KanbanBoardRepository extends MongoRepository<KanbanBoard,String> {
    Optional<KanbanBoard> findById(String KanbanBoardId);

}

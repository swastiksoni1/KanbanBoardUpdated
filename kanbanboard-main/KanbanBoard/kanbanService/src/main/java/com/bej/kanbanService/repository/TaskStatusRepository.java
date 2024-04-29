package com.bej.kanbanService.repository;

import com.bej.kanbanService.domain.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskStatusRepository extends MongoRepository<TaskStatus, Long > {
    Optional<TaskStatus> findByValue(String taskStatus);
}

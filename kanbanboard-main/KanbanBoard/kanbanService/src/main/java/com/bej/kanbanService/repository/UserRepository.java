package com.bej.kanbanService.repository;

import com.bej.kanbanService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository <User, String>{
    Optional<User> findById(String userId);
//    User findByBoardId(String boardId);

}

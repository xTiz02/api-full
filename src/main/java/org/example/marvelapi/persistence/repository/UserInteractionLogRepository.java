package org.example.marvelapi.persistence.repository;

import org.example.marvelapi.persistence.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionLogRepository extends MongoRepository<UserInteractionLog, String> {

    Page<UserInteractionLog> findByUsername(Pageable pageable, String username);
}

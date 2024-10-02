package org.example.marvelapi.persistence.repository;

import org.example.marvelapi.persistence.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findByUsername(String username);
}

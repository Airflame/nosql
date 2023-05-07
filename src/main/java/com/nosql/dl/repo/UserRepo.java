package com.nosql.dl.repo;

import com.nosql.dl.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
}

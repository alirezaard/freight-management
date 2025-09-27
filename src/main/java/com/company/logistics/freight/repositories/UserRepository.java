package com.company.logistics.freight.repositories;

import com.company.logistics.freight.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    void deleteUserByUsername(String username);
    boolean existsUserByUsername(String username);
}

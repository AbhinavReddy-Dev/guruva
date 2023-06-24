package dev.abhinavreddy.guruva.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

//    query to get user by username
    @Query(value = "{ 'username' : ?0 }", fields = "{ 'password' : 0, 'userToken':  0, 'isDeleted':  0}")
    Optional<User> findByUsername(String username);

//    query to get user password by username
    @Query(value = "{ 'username' : ?0 }", fields = "{ 'password' : 1}")
    Optional<User> findPasswordByUsername(String username);
}

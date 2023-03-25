package dev.abhinavreddy.guruva.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query(value = "{ 'username' : ?0 }", fields = "{ 'password' : 0, 'userToken':  0, 'email': 0, 'skillsGotHelp':  0, 'skillsNeedHelp': 0}")
    Optional<User> findByUsername(String username);
}

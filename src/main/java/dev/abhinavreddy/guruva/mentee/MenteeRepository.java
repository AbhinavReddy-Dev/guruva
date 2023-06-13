package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MenteeRepository extends MongoRepository<Mentee, ObjectId> {
//  Query all records where mentor is null
    @Query(value = "{ 'mentor' : null }")
    Iterable<Mentee> findAllByMentorNull();

//  Query all records with isClosed false
    @Query(value = "{ 'isClosed' : false }")
    Iterable<Mentee> findAllByIsClosedFalse();

//  Query all records where mentee is user
    @Query(value = "{ 'mentee' : ?0 }")
    Iterable<Mentee> findAllByMentee(User mentee);

//  Query all records where mentor is user
    @Query(value = "{ 'mentor' : ?0 }")
    Iterable<Mentee> findAllByMentor(User mentor);

//  Query to find all records with learningMode
    @Query(value = "{ 'learningMode' : ?0 }")
    Iterable<Mentee> findAllByLearningMode(String learningMode);
}

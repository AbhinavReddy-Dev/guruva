package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MentorRepository extends MongoRepository<Mentor, ObjectId> {

//  Query all records where isDeleted is false
    @Query(value = "{ 'isDeleted' : false }")
    Iterable<Mentor> findAllByIsDeletedFalse();

//  Query all records where mentor is user
    @Query(value = "{ 'mentor' : ?0 }")
    Iterable<Mentor> findAllByMentor(User mentor);

//    Query to find all records with learningMode
    @Query(value = "{ 'learningMode' : ?0 }")
    Iterable<Mentor> findAllByLearningMode(String learningMode);

//    Query to find all records with mentors available
    @Query(value = "{ 'isAvailable' : true }")
    Iterable<Mentor> findAllByIsAvailableTrue();

//    Query all records with rating
    @Query(value = "{ 'rating' : ?0 }")
    Iterable<Mentor> findAllByRating(Integer rating);
}

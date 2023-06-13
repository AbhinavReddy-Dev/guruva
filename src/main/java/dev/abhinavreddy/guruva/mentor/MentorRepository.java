package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MentorRepository extends MongoRepository<Mentor, ObjectId> {

//  Query all records where mentor is user
    @Query(value = "{ 'mentor' : ?0 }")
    Iterable<Mentee> findAllByMentor(User mentor);

//    Query to find all records with learningMode
    @Query(value = "{ 'learningMode' : ?0 }")
    Iterable<Mentee> findAllByLearningMode(String learningMode);

//    Query to find all records with mentors available
    @Query(value = "{ 'isAvailable' : true }")
    Iterable<Mentee> findAllByIsAvailableTrue();

//    Query all records with rating
    @Query(value = "{ 'rating' : ?0 }")
    Iterable<Mentee> findAllByRating(Integer rating);
}

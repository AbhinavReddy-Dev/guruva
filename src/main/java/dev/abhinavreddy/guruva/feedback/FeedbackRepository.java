package dev.abhinavreddy.guruva.feedback;

import dev.abhinavreddy.guruva.customtypes.FeedbackType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FeedbackRepository extends MongoRepository<Feedback, ObjectId> {

//    Query feedback by byUser and type
    @Query(value = "{ 'byUser' : ?0, 'type' : ?1, 'isDeleted' : false }")
    Iterable<Feedback> findAllByUserAndType(ObjectId byUser, FeedbackType type);
//    Query feedback by forUser and type
    @Query(value = "{ 'forUser' : ?0, 'type' : ?1, 'isDeleted' : false }")
    Iterable<Feedback> findAllForUserAndType(ObjectId forUser, FeedbackType type);
//    Query feedback for a mentor
    @Query(value="{'mentor': ?0, 'isDeleted': false}")
    Iterable<Feedback> findAllByMentor(ObjectId mentor);
//    Query feedback for a mentee
    @Query(value="{'mentee': ?0, 'isDeleted': false}")
    Iterable<Feedback> findAllByMentee(ObjectId mentee);

//    Query to get feedback by mentorId and menteeId
    @Query(value = "{ 'mentor' : ?0, 'mentee' : ?1, 'isDeleted' : false }")
    Iterable<Feedback> findAllByMentorAndMentee(ObjectId mentor, ObjectId mentee);
}

package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MentorRepository extends MongoRepository<Mentor, ObjectId> {

//  Query all records where isDeleted is false
    @Query(value = "{ 'mentor': ?0 ,'isDeleted' : true }")
    Iterable<Mentor> findAllDeletedByMentor(ObjectId mentor);

//  Query all records where mentor is user
    @Query(value = "{ 'mentor' : ?0, isDeleted: false }")
    Iterable<Mentor> findAllByMentor(ObjectId mentor);

}

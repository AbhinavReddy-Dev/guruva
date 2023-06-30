package dev.abhinavreddy.guruva.mentor;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MentorRepository extends MongoRepository<Mentor, ObjectId> {

//    Query mentor by id and isDeleted is false
    @Query(value = "{ '_id' : ?0, 'isDeleted' : false }")
    Optional<Mentor> findByIdAndIsDeletedFalse(ObjectId id);

//  Query all records where isDeleted is false
    @Query(value = "{ 'mentor': ?0 ,'isDeleted' : true }")
    Iterable<Mentor> findAllDeletedByMentor(ObjectId mentor);

//  Query all records where mentor is user
    @Query(value = "{ 'mentor' : ?0, isDeleted: false }")
    Iterable<Mentor> findAllByMentor(ObjectId mentor);

}

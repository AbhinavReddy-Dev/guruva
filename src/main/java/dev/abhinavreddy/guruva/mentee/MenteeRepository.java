package dev.abhinavreddy.guruva.mentee;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MenteeRepository extends MongoRepository<Mentee, ObjectId> {

//  Query all records where mentee is user
    @Query(value = "{ 'mentee' : ?0, isDeleted: false }")
    Iterable<Mentee> findAllByMentee(ObjectId mentee);

//  Query all deleted records where mentee is user
    @Query(value = "{ 'mentee' : ?0, isDeleted: true }")
    Iterable<Mentee> findAllDeletedByMentee(ObjectId mentee);

}

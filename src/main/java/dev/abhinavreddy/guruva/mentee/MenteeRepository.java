package dev.abhinavreddy.guruva.mentee;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenteeRepository extends MongoRepository<Mentee, ObjectId> {
}

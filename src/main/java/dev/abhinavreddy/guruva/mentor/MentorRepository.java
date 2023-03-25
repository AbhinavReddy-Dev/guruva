package dev.abhinavreddy.guruva.mentor;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MentorRepository extends MongoRepository<Mentor, ObjectId> {
}

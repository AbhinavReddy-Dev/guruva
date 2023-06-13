package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.mentee.MenteeRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;
    private final MenteeRepository menteeRepository;
    private final MongoTemplate mongoTemplate;
    public MentorService(MentorRepository mentorRepository, UserRepository userRepository, MenteeRepository menteeRepository, MongoTemplate mongoTemplate) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.menteeRepository = menteeRepository;
        this.mongoTemplate = mongoTemplate;
    }

//    create mentor
    public Mentor createMentor(Mentor mentor, String username) {
        // set user as mentor
        User user = userRepository.findByUsernameForProfile(username).orElse(null);
        assert user != null;
        mentor.setMentor(user);
        mentor.setCreatedAt(java.time.LocalDateTime.now());
        // update mentor with user
        return mentorRepository.insert(mentor);
    }

//    save mentee based on mentor objectId and mentee username using mongo template
    public Mentor createMenteeForMentor(ObjectId mentorId, String menteeUserName) {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        assert mentor != null;
        // set user as mentee
        User user = userRepository.findByUsernameForProfile(menteeUserName).orElse(null);
        assert user != null;
        mentor.setUpdatedAt(java.time.LocalDateTime.now());
        // Mentee object to be created and saved in mentee collection
        Mentee mentee = new Mentee();
        mentee.setMentee(user);
        mentee.setMentor(mentor.getMentor());
        mentee.setLearningMode(mentor.getLearningMode());
        mentee.setSkills(mentor.getSkills());
        mentee.setCreatedAt(java.time.LocalDateTime.now());
        mongoTemplate.save(mentee, "mentee");
        // update mentor with user
        return mentorRepository.save(mentor);
    }

}

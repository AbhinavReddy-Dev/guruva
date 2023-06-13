package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MenteeService {
    private final MenteeRepository menteeRepository;
    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;
    private final MongoTemplate mongoTemplate;
// constructor injection for menteeRepository and userRepository
    public MenteeService(MenteeRepository menteeRepository, UserRepository userRepository, MentorRepository mentorRepository, MongoTemplate mongoTemplate) {
        this.menteeRepository = menteeRepository;
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
        this.mongoTemplate = mongoTemplate;
    }

// create mentee
    public Mentee createMentee(Mentee mentee, String username) {
        // set user as mentee
        User user = userRepository.findByUsernameForProfile(username).orElse(null);
        assert user != null;
        mentee.setMentee(user);
        mentee.setCreatedAt(java.time.LocalDateTime.now());
        // update mentee with user
        return menteeRepository.insert(mentee);
    }

// Add Mentor by username and create mentor based on Mentee details using mongo template
    public Mentee addMentor(String mentorUserName, ObjectId menteeId) {
        Mentee mentee = menteeRepository.findById(menteeId).orElse(null);
        assert mentee != null;
        // set user as mentor
        User user = userRepository.findByUsernameForProfile(mentorUserName).orElse(null);
        assert user != null;
        mentee.setMentor(user);
        mentee.setUpdatedAt(java.time.LocalDateTime.now());
        // Mentor object to be created and saved in mentor collection
        Mentor mentor = new Mentor();
        mentor.setMentor(user);
        mentor.setCreatedAt(java.time.LocalDateTime.now());
        mentor.setLearningMode(mentee.getLearningMode());
        mentor.setSkills(mentee.getSkills());
        mongoTemplate.save(mentor, "mentor");
        // update mentee with user
        return menteeRepository.save(mentee);
    }


// add mentor to mentee
    public Mentee addMentor(ObjectId menteeId, String username) {
        Mentee mentee = menteeRepository.findById(menteeId).orElse(null);
        assert mentee != null;
        User user = userRepository.findByUsernameForProfile(username).orElse(null);
        assert user != null;
        mentee.setMentor(user);
        mentee.setUpdatedAt(java.time.LocalDateTime.now());
        return menteeRepository.save(mentee);
    }

// update mentee
    public Mentee updateMentee(Mentee mentee) {
        mentee.setUpdatedAt(java.time.LocalDateTime.now());
        return menteeRepository.save(mentee);
    }

// close  mentee
    public Mentee closeMentee(ObjectId id) {
        Mentee mentee = menteeRepository.findById(id).orElse(null);
        assert mentee != null;
        mentee.setIsClosed(true);
        mentee.setClosedAt(java.time.LocalDateTime.now());
        return menteeRepository.save(mentee);
    }

// get mentee by id
    public Mentee getMentee(ObjectId id) {
        return menteeRepository.findById(id).orElse(null);
    }

// get all mentees without mentor
    public Iterable<Mentee> getAllMenteesWithoutMentor() {
        return menteeRepository.findAllByMentorNull();
    }

// get all isClosed False mentees
    public Iterable<Mentee> getAllOpenMentees() {
        return menteeRepository.findAllByIsClosedFalse();
    }

// get all mentees by mentee username
    public Iterable<Mentee> getAllMenteesByMenteeUsername(String username) {
        return menteeRepository.findAllByMentee(userRepository.findByUsernameForProfile(username).orElse(null));
    }

// get all mentees by mentor username
    public Iterable<Mentee> getAllMenteesByMentorUsername(String username) {
        return menteeRepository.findAllByMentor(userRepository.findByUsernameForProfile(username).orElse(null));
    }

// get all by learningMode
    public Iterable<Mentee> getAllMenteesByLearningMode(String learningMode) {
        return menteeRepository.findAllByLearningMode(learningMode);
    }

//
// delete mentee
    public void deleteMentee(ObjectId id) {
        menteeRepository.deleteById(id);
    }

}

package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
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
        User user = userRepository.getByUsernameForProfile(username).orElse(null);
        assert user != null;
        mentee.setMentee(user);
        // update mentee with user
        return menteeRepository.insert(mentee);
    }

// Add Mentor by username and create mentor based on Mentee details using mongo template
    public Mentor createMentorForMentee( ObjectId menteeId, String mentorUserName) {
        Mentee mentee = menteeRepository.findById(menteeId).orElse(null);
        assert mentee != null;

        // create mentor based on mentee details
        User user = userRepository.getByUsernameForProfile(mentorUserName).orElse(null);
        assert user != null;

        // Mentor object to be created and saved in mentor collection
        Mentor mentor = new Mentor();
        mentor.setMentor(user);
        mentor.setSkills(mentee.getSkills());
        mentor.setLearningMode(mentee.getLearningMode());
        mentor.setIsAvailable(true);
        mentorRepository.save(mentor);

        // update mentee with mentor
        mentee.setMentor(mentor.getMentor());
        mentee.setIsClosed(true);
        menteeRepository.save(mentee);
        return mentor;
    }

// update mentee skills or learningMode
    public Mentee updateMentee(Mentee mentee, ObjectId menteeId) {
        Mentee mentee1 = menteeRepository.findById(menteeId).orElse(null);
        assert mentee1 != null;
        mentee1.setLearningMode(mentee.getLearningMode());
        mentee1.setSkills(mentee.getSkills());
        return menteeRepository.save(mentee1);
    }

//    remove mentor and update isClosed to false
    public Mentee removeMentor(ObjectId menteeId) {
        Mentee mentee = menteeRepository.findById(menteeId).orElse(null);
        assert mentee != null;
        mentee.setMentor(null);
        mentee.setIsClosed(false);
        return menteeRepository.save(mentee);
    }

// close mentee
    public Mentee closeMentee(ObjectId id) {
        Mentee mentee = menteeRepository.findById(id).orElse(null);
        assert mentee != null;
        mentee.setIsClosed(true);
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
        return menteeRepository.findAllByMentee(userRepository.getByUsernameForProfile(username).orElse(null));
    }

// get all mentees by mentor username
    public Iterable<Mentee> getAllMenteesByMentorUsername(String username) {
        return menteeRepository.findAllByMentor(userRepository.getByUsernameForProfile(username).orElse(null));
    }

// get all by learningMode
    public Iterable<Mentee> getAllMenteesByLearningMode(String learningMode) {
        return menteeRepository.findAllByLearningMode(learningMode);
    }

// delete mentee from user view
    public Mentee deleteMentee(ObjectId id) {
        Mentee mentee = menteeRepository.findById(id).orElse(null);
        assert mentee != null;
        mentee.setIsDeleted(true);
        return menteeRepository.save(mentee);
    }

}

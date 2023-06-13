package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;

public class MenteeService {
    private final MenteeRepository menteeRepository;
    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;

// constructor injection for menteeRepository and userRepository
    public MenteeService(MenteeRepository menteeRepository, UserRepository userRepository, MentorRepository mentorRepository) {
        this.menteeRepository = menteeRepository;
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
    }

// create mentee
    public Mentee createMentee(Mentee mentee, String username) {
        // set user as mentee
        User user = userRepository.findByUsernameForProfile(username).orElse(null);
        assert user != null;
        mentee.setMentee(user);
        mentee.setCreatedAt(java.time.LocalDateTime.now());
        // update mentee with user
        return menteeRepository.save(mentee);
    }

//    Create a Mentee from Mentor ObjectId
    public Mentee createMenteeFromMentor(ObjectId id, String username) {
        Mentee mentee = new Mentee();
        // set user as mentee
        User user = userRepository.findByUsernameForProfile(username).orElse(null);
        assert user != null;
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        assert mentor != null;
        mentee.setMentee(user);
        mentee.setMentor(mentor.getMentor());
        mentee.setCreatedAt(java.time.LocalDateTime.now());
        mentee.setSkills(mentor.getSkills());
        mentee.setLearningMode(mentor.getLearningMode());
        mentee.setIsClosed(false);
        // update mentee with user
        return menteeRepository.save(mentee);
    }

// add mentor to mentee
    public Mentee addMentor(ObjectId id, String username) {
        Mentee mentee = menteeRepository.findById(id).orElse(null);
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

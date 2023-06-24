package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.mentee.MenteeRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
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
        User user = userRepository.findByUsername(username).orElse(null);
        assert user != null;

        mentor.setMentor(user);
        System.out.println(mentor);
        // update mentor with user
        return mentorRepository.save(mentor);
    }

//    save mentee based on mentor objectId and mentee username using mongo template
    public Mentee createMenteeForMentor(ObjectId mentorId, String menteeUserName) throws Exception {
        Mentor mentor;
        User user;
        try {
            mentor = mentorRepository.findById(mentorId).orElse(null);
            assert mentor != null;
        }
        catch (Exception e) {
            throw new Exception("Mentor entry not found.");
        }
        // Mentee object to be created and saved in mentee collection from mentor details
        try {
            user = userRepository.findByUsername(menteeUserName).orElse(null);
            assert user != null;
        } catch (Exception e) {
            throw new Exception("Mentee user not found: " + menteeUserName);
        }
        Mentee mentee = new Mentee();
        mentee.setMentee(user);
        mentee.setMentor(mentor.getMentor());
        mentee.setLearningMode(mentor.getLearningMode());
        mentee.setSkills(mentor.getSkills());
        mentee.setIsClosed(true);
        //  mongoTemplate.save(mentee, "mentee");
        menteeRepository.save(mentee);
        return mentee;
    }

//    get all mentors not deleted
    public Iterable<Mentor> getAllMentors() {
        return mentorRepository.findAllByIsDeletedFalse();
    }

//    get all mentors by username
    public Iterable<Mentor> getAllMentorsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return mentorRepository.findAllByMentor(user);
    }

//    get all by learning mode
    public Iterable<Mentor> getAllMentorsByLearningMode(String learningMode) {
        return mentorRepository.findAllByLearningMode(learningMode);
    }

//    get all by is available
    public Iterable<Mentor> getAllMentorsByIsAvailable() {
        return mentorRepository.findAllByIsAvailableTrue();
    }

//    get all by rating
    public Iterable<Mentor> getAllMentorsByRating(Integer rating) {
        return mentorRepository.findAllByRating(rating);
    }

//    delete mentor
    public Mentor deleteMentor(ObjectId mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        assert mentor != null;
        mentor.setIsAvailable(false);
        mentor.setIsDeleted(true);

        return mentorRepository.save(mentor);
    }
}

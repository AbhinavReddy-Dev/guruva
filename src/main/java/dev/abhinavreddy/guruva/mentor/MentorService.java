package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.exceptions.UserNotFound;
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
        try {        // TODO: check if logged in username is mentee username
            Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new Exception("Mentor not found: " + mentorId));
            assert mentor != null;
            // Mentee object to be created and saved in mentee collection from mentor details
            User user = userRepository.findByUsername(menteeUserName).orElseThrow(() -> new UserNotFound("Mentee user not found: " + menteeUserName));

            Mentee mentee = new Mentee();
            mentee.setMentee(user);
            mentee.setMentor(mentor.getMentor());
            mentee.setLearningMode(mentor.getLearningMode());
            mentee.setSkills(mentor.getSkills());
            mentee.setIsOpen(true);
            //  mongoTemplate.save(mentee, "mentee");
            menteeRepository.save(mentee);
            return mentee;
        }
        catch(UserNotFound e){
            throw e;
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }

//    get by mentor id
    public Mentor getMentorById(ObjectId mentorId) throws Exception {
        try {
            return mentorRepository.findById(mentorId).orElseThrow(() -> new RuntimeException("Mentor not found."));
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }

//    get all mentors by username
    public Iterable<Mentor> getAllMentorsByMentorUsername(String username) throws Exception {
        try {
            return mentorRepository.findAllByMentor(userRepository.findByUsername(username).orElseThrow(()-> new Exception("User not found.")).getId());
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }

//    get all deleted mentors for user
    public Iterable<Mentor> getAllDeletedMentorsByMentorUsername(String username) throws  Exception {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(()->
                    new Exception("User not found: " + username));
//            TODO: check if logged in user is username
            return mentorRepository.findAllDeletedByMentor(user.getId());
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }

//    update mentor availability by mentorId
    public Mentor updateMentorAvailability(ObjectId mentorId, Boolean isAvailable) throws Exception {
        try {
            Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new Exception("Mentor not found: " + mentorId));
            assert mentor != null;
//            TODO: check if logged in user is mentor
            mentor.setIsAvailable(isAvailable);
            return mentorRepository.save(mentor);
        } catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }
//    remove mentee using mentorId and MenteeId
    public Boolean removeMentorForMentee(ObjectId menteeId) throws Exception {
        try {

            Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new Exception("Mentee not found: " + menteeId));
            assert mentee != null;
            if (mentee.getMentor() != null ) { // TODO: and check if logged in user is mentor of mentee
                mentee.setMentor(null);
                mentee.setIsOpen(true);
                menteeRepository.save(mentee);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }

//    delete mentor
    public void deleteMentor(ObjectId mentorId) throws Exception {
        try {
            Mentor mentor = mentorRepository.findById(mentorId).orElseThrow( () -> new Exception("Mentor not found."));
            assert mentor != null;
            // TODO: check if logged in user is mentor
            mentor.setIsAvailable(false);
            mentor.setIsDeleted(true);
            mentorRepository.save(mentor);
        }
        catch (Exception e) {
            throw new Exception( e.getLocalizedMessage());
        }
    }
}

package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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
    public Mentee createMentee(Mentee mentee, String username) { // ✅
        // set user as mentee
        User user = userRepository.findByUsername(username).orElse(null);
        assert user != null;
        mentee.setMentee(user);
        // update mentee with user
        return menteeRepository.insert(mentee);
    }

// Add Mentor by username and create mentor based on Mentee details using mongo template
    public Mentor createMentorForMentee( ObjectId menteeId, String mentorUserName) throws Exception { // ✅
        try {
            Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new Exception("Mentee not found: " + menteeId));
            assert mentee != null;

            // create mentor based on mentee details
            User user = userRepository.findByUsername(mentorUserName).orElseThrow(() -> new Exception("Mentor user not found: " + mentorUserName));
            assert user != null;

            // Mentor object to be created and saved in mentor collection
            Mentor mentor = new Mentor();
            mentor.setMentor(user);
            mentor.setSkills(mentee.getSkills());
            mentor.setLearningMode(mentee.getLearningMode());
            mentor.setIsAvailable(true);
            mentorRepository.save(mentor);

            // update mentee with mentor
            Query query = new Query().addCriteria(Criteria.where("_id").is(menteeId));
            Update update = new Update().set("mentor", mentor);
            mongoTemplate.updateFirst(query, update, Mentee.class);
            return mentor;
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

    // get mentee by id
    public Mentee getMenteeById(ObjectId id) throws Exception { // ✅
        try{
            return menteeRepository.findById(id).orElseThrow(() -> new Exception("Mentee not found: " + id));
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

    // get all mentees by mentee username
    public Iterable<Mentee> getAllMenteesByMenteeUsername(String username) throws Exception { // ✅
        try {
            return menteeRepository.findAllByMentee(userRepository.findByUsername(username).orElseThrow( () -> new Exception("User not found: " + username)).getId());
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

//    get all deleted by mentee username
    public Iterable<Mentee> getAllDeletedByMenteeUsername(String username) throws Exception { // ✅
        try {
            return menteeRepository.findAllDeletedByMentee(userRepository.findByUsername(username).orElseThrow( () -> new Exception("User not found: " + username)).getId());
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

// remove mentor and update isClosed to false
    public Mentee removeMentor(ObjectId menteeId) throws Exception { // ✅
        try {
            Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new Exception("Mentee not found: " + menteeId));
            assert mentee != null;
            //  TODO: check if logged-in user and mentee are the same before removing mentor, else throw an exception
            if(mentee.getMentor() != null) {
                Query query = new Query().addCriteria(Criteria.where("_id").is(menteeId));
                Update update = new Update().set("mentor", null);
                mentee.setMentor(null);
                if (!mentee.getIsOpen()) {
                    mentee.setIsOpen(true);
                    update.set("isOpen", true);
                }
                mongoTemplate.updateFirst(query, update, Mentee.class);
                return mentee;
            }
            else {
                throw new Exception("Mentor not found to remove for mentee: " + menteeId);
            }

        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

// close mentee
    public Mentee closeMentee(ObjectId id) throws Exception { // ✅
        try {
            Mentee mentee = menteeRepository.findById(id).orElseThrow( () -> new Exception("Mentee not found: " + id));
            assert mentee != null;
//  TODO: check if logged-in user and mentee are the same before closing, else throw an exception
            if(!mentee.getIsOpen()) {
                throw new Exception("Mentee already closed: " + id);
            }
            Query query = new Query().addCriteria(Criteria.where("_id").is(id));
            Update update = new Update().set("isOpen", false);
            mongoTemplate.updateFirst(query, update, Mentee.class);
            mentee.setIsOpen(false);
            return mentee;
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

// delete mentee
    public boolean deleteMentee(ObjectId id) throws Exception { // ✅
        try {
            Mentee mentee = menteeRepository.findById(id).orElse(null);
            assert mentee != null;
            if(mentee.getIsDeleted()) {
                throw new Exception("Mentee already deleted: " + id);
            }
            //  TODO: check if logged-in user and mentee are the same before deleting, else throw an exception
            Query query = new Query().addCriteria(Criteria.where("_id").is(id));
            Update update = new Update().set("isDeleted", true);
            mongoTemplate.updateFirst(query, update, Mentee.class);
            return true;
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

}

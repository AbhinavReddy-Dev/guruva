package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
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
            mentee.setMentor(mentor.getMentor());
            mentee.setIsOpen(true);
            menteeRepository.save(mentee);
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
                mentee.setMentor(null);
                if (!mentee.getIsOpen())
                    mentee.setIsOpen(true);
                return menteeRepository.save(mentee);
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
            mentee.setIsOpen(false);
            return menteeRepository.save(mentee);
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
            mentee.setIsDeleted(true);
            menteeRepository.save(mentee);
            return true;
        }
        catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

}

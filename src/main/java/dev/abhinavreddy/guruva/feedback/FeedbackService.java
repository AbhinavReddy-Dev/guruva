package dev.abhinavreddy.guruva.feedback;

import dev.abhinavreddy.guruva.customtypes.FeedbackType;
import dev.abhinavreddy.guruva.exceptions.UserNotFound;
import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.mentee.MenteeRepository;
import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.mentor.MentorRepository;
import dev.abhinavreddy.guruva.reqbodytypes.CreateFeedback;
import dev.abhinavreddy.guruva.user.User;
import dev.abhinavreddy.guruva.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, UserRepository userRepository, MenteeRepository menteeRepository, MentorRepository mentorRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.menteeRepository = menteeRepository;
        this.mentorRepository = mentorRepository;
    }

//    create feedback
    public Feedback createFeedback(CreateFeedback createFeedback) throws Exception {
       try {
                System.out.println("Inside create feedback service");
               if(createFeedback.getType() == null) {
                   throw new IllegalArgumentException("Feedback type cannot be null");
               }

               if(createFeedback.getType() != FeedbackType.MENTEE_TO_MENTOR && createFeedback.getType() != FeedbackType.MENTOR_TO_MENTEE) {
                   throw new IllegalArgumentException("Feedback type should be either MENTOR_FOR_MENTEE or MENTEE_FOR_MENTOR");
               }
               //  check if byUser and forUser are same
               if(createFeedback.getByUsername().equals(createFeedback.getForUsername())) {
                   throw new IllegalArgumentException("Feedback author cannot be same as feedback receiver.");
               }

               if (createFeedback.getRating() < 0.0 || createFeedback.getRating() > 5.0 || createFeedback.getRating() == null) {
                   throw new IllegalArgumentException("Rating should be between 0 and 5");
               }

               if(createFeedback.getComment().length() > 1000) {
                   throw new IllegalArgumentException("Comment should be less than 1000 characters");
               }
               if(createFeedback.getComment().length() == 0) {
                   throw new IllegalArgumentException("Comment should not be empty");
               }

               User byUser = userRepository.findByUsername(createFeedback.getByUsername()).orElseThrow( () -> new UserNotFound("Feedback author not found."));
               User forUser = userRepository.findByUsername(createFeedback.getForUsername()).orElseThrow( () -> new UserNotFound("Feedback receiver not found."));
               Mentee mentee = menteeRepository.findByIdAndIsDeletedFalse(createFeedback.getMenteeId()).orElseThrow( () -> new Exception("Mentee not found: " + createFeedback.getMenteeId()));
               Mentor mentor = mentorRepository.findByIdAndIsDeletedFalse(createFeedback.getMentorId()).orElseThrow( () -> new Exception("Mentor not found: " + createFeedback.getMentorId()));

               if(feedbackRepository.findAllByMentorAndMentee(mentor.getId(), mentee.getId()).iterator().hasNext()) {
                   throw new IllegalArgumentException("Feedback already exists");
               }

               if(createFeedback.getType() == FeedbackType.MENTEE_TO_MENTOR) {
                   if(!mentee.getMentee().getUsername().equals(byUser.getUsername())) {
                       throw new IllegalArgumentException("Feedback author is not the mentee");
                   }
                   if(!mentor.getMentor().getUsername().equals(forUser.getUsername())) {
                       throw new IllegalArgumentException("Feedback receiver is not the mentor");
                   }
               }
               else {
                   if(!mentor.getMentor().getUsername().equals(byUser.getUsername())) {
                       throw new IllegalArgumentException("Feedback author is not the mentor");
                   }
                   if(!mentee.getMentee().getUsername().equals(forUser.getUsername())) {
                       throw new IllegalArgumentException("Feedback receiver is not the mentee");
                   }
               }

               Feedback feedback = new Feedback();
               //           set feedback type
               feedback.setType(createFeedback.getType());
               //            set byUser and forUser
               feedback.setByUser(byUser);
               feedback.setForUser(forUser);
               //            mentor and mentee
               feedback.setMentee(mentee);
               feedback.setMentor(mentor);
               //            set rating
               feedback.setRating(createFeedback.getRating());
               //            set comment
               feedback.setComment(createFeedback.getComment());
               return feedbackRepository.save(feedback);
        }
       catch(IllegalArgumentException | UserNotFound | MethodArgumentTypeMismatchException e) {
           throw e;
         } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    get all feedback for a user (username) for type (type)
    public Iterable<Feedback> getFeedbackForUserAndType(String username, String type) throws Exception {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
            return feedbackRepository.findAllForUserAndType(user.getId(), FeedbackType.valueOf(type));
        }
        catch(UserNotFound e) {
            throw e;
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    get all feedback by a user (username) for type (type)
    public Iterable<Feedback> getFeedbackByUserAndType(String username, String type) throws Exception {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
            return feedbackRepository.findAllByUserAndType(user.getId(), FeedbackType.valueOf(type));
        }
        catch(UserNotFound e) {
            throw e;
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    get all feedback for a mentor (mentorId)
    public Iterable<Feedback> getFeedbackForMentor(ObjectId mentorId) throws Exception {
        try {
            Mentor mentor = mentorRepository.findByIdAndIsDeletedFalse(mentorId).orElseThrow( () -> new Exception("Mentor not found: " + mentorId));
            return feedbackRepository.findAllByMentor(mentor.getId());
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    get all feedback for a mentee (menteeId)
    public Iterable<Feedback> getFeedbackForMentee(ObjectId menteeId) throws Exception {
        try {
            Mentee mentee = menteeRepository.findByIdAndIsDeletedFalse(menteeId).orElseThrow( () -> new Exception("Mentee not found: " + menteeId));
            return feedbackRepository.findAllByMentee(mentee.getId());
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

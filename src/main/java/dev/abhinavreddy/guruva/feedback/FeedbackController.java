package dev.abhinavreddy.guruva.feedback;

import dev.abhinavreddy.guruva.config.ResponseBody;
import dev.abhinavreddy.guruva.reqbodytypes.CreateFeedback;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


//    create feedback
    @PostMapping("/create")
    public ResponseEntity<ResponseBody> createFeedback(@RequestBody CreateFeedback createFeedback) throws Exception {
        System.out.println("Inside create feedback: " + createFeedback);

        Feedback newFeedback = feedbackService.createFeedback(createFeedback);

        ResponseBody responseBody = new ResponseBody("Feedback added successfully!", false, HttpStatus.OK, newFeedback);
        return ResponseEntity.ok(responseBody);
    }

//    get all feedback for a user (username) for type (type)
    @GetMapping("/get_all_for_user_and_type")
    public ResponseEntity<ResponseBody> getFeedback(@RequestBody Map<String, String> reqBody) throws Exception {
        Iterable<Feedback> feedback = feedbackService.getFeedbackForUserAndType(reqBody.get("username"), reqBody.get("type"));

        ResponseBody responseBody = new ResponseBody("Feedback found!", false, HttpStatus.OK, feedback);
        if(!feedback.iterator().hasNext()) {
            responseBody.setMessage("No feedback found!");
        }
        return ResponseEntity.ok(responseBody);
    }

//    get all feedback by a user (username) for type (type)
    @GetMapping("/get_all_by_user_and_type")
    public ResponseEntity<ResponseBody> getFeedbackByUserAndType(@RequestBody Map<String, String> reqBody) throws Exception {
        Iterable<Feedback> feedback = feedbackService.getFeedbackByUserAndType(reqBody.get("username"), reqBody.get("type"));

        ResponseBody responseBody = new ResponseBody("Feedback found!", false, HttpStatus.OK, feedback);
        if(!feedback.iterator().hasNext()) {
            responseBody.setMessage("No feedback found!");
        }
        return ResponseEntity.ok(responseBody);
    }

//    get all feedback for a mentor (mentorId)
    @GetMapping("/get_all_for_mentor")
    public ResponseEntity<ResponseBody> getFeedbackForMentor(@RequestBody Map<String, ObjectId> reqBody) throws Exception {
        Iterable<Feedback> feedback = feedbackService.getFeedbackForMentor(reqBody.get("mentorId"));

        ResponseBody responseBody = new ResponseBody("Feedback found!", false, HttpStatus.OK, feedback);
        if(!feedback.iterator().hasNext()) {
            responseBody.setMessage("No feedback found!");
        }
        return ResponseEntity.ok(responseBody);
    }

//    get all feedback for a mentee (menteeId)
    @GetMapping("/get_all_for_mentee")
    public ResponseEntity<ResponseBody> getFeedbackForMentee(@RequestBody Map<String, ObjectId> reqBody) throws Exception {
        Iterable<Feedback> feedback = feedbackService.getFeedbackForMentee(reqBody.get("menteeId"));

        ResponseBody responseBody = new ResponseBody("Feedback found!", false, HttpStatus.OK, feedback);
        if(!feedback.iterator().hasNext()) {
            responseBody.setMessage("No feedback found!");
        }
        return ResponseEntity.ok(responseBody);
    }

//    update feedback (rating, comment)

//   delete feedback (isDeleted = true)

}

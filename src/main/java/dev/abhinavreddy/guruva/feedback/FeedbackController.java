package dev.abhinavreddy.guruva.feedback;

import dev.abhinavreddy.guruva.config.ResponseBody;
import dev.abhinavreddy.guruva.customtypes.FeedbackType;
import dev.abhinavreddy.guruva.reqbodytypes.CreateFeedback;
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
        Iterable<Feedback> feedback = feedbackService.getFeedback(reqBody.get("username"), reqBody.get("type"));
        ResponseBody responseBody = new ResponseBody("Feedback found!", false, HttpStatus.OK, feedback);
        return ResponseEntity.ok(responseBody);
    }
//    get all feedback by a user (username) for type (type)

//    get all feedback for a mentor (mentorId)

//    get all feedback for a mentee (menteeId)

//    update feedback (rating, comment)

//   delete feedback (isDeleted = true)

}

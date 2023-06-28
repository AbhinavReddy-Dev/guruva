package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.reqbodytypes.CreateMentee;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMentorForMentee;
import dev.abhinavreddy.guruva.mentor.Mentor;
import dev.abhinavreddy.guruva.reqres.ResponseBody;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mentee")

public class MenteeController {
    private final MenteeService menteeService;
    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Mentee> createMentee(@RequestBody CreateMentee requestBody){
        System.out.println("Inside create mentee: " + requestBody.getMentee());
        Mentee newMentee = menteeService.createMentee(requestBody.getMentee(), requestBody.getUsername());
        return ResponseEntity.ok(newMentee);
    }

    @PostMapping("/create_mentor_for_mentee")
    public ResponseEntity<Mentor> createMentorForMentee(@RequestBody CreateMentorForMentee requestBody) throws Exception {
        System.out.println("Inside create mentor for mentee: " + requestBody.getMenteeId() + " " + requestBody.getMentorUsername());
        Mentor newMentor = menteeService.createMentorForMentee(requestBody.getMenteeId(), requestBody.getMentorUsername());
        return ResponseEntity.ok(newMentor);
    }

//    get mentee by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseBody> getMentee(@PathVariable ObjectId id) throws Exception {
        System.out.println("Inside get mentee: " + id);
        Mentee mentee = menteeService.getMenteeById(id);
        ResponseBody responseBody = new ResponseBody("Mentee with id: " + id, false, HttpStatus.OK, mentee);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/get_all/{username}")
    public ResponseEntity<ResponseBody> getMentee(@PathVariable String username) throws Exception {
        System.out.println("Inside get mentees: " + username);
        Iterable<Mentee> allMentees = menteeService.getAllMenteesByMenteeUsername(username);
        ResponseBody responseBody = new ResponseBody("All mentees of: " + username, false, HttpStatus.OK, allMentees);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("close_mentee/{menteeId}")
    public ResponseEntity<ResponseBody> closeMentee(@PathVariable ObjectId menteeId) throws Exception {
        System.out.println("Inside close mentee: " + menteeId);
        Mentee mentee = menteeService.closeMentee(menteeId);
        ResponseBody responseBody = new ResponseBody("Mentee closed successfully!", false, HttpStatus.OK, mentee);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("remove_mentor/{menteeId}")
    public ResponseEntity<ResponseBody> removeMentor(@PathVariable ObjectId menteeId) throws Exception {
        System.out.println("Inside remove mentor: " + menteeId);
        Mentee mentee = menteeService.removeMentor(menteeId);
        ResponseBody responseBody = new ResponseBody("Mentor removed successfully!", false, HttpStatus.OK, mentee);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody> deleteMentee(@PathVariable ObjectId id) throws Exception {
        System.out.println("Inside delete mentee: " + id);
        Mentee mentee = menteeService.deleteMentee(id);
        ResponseBody responseBody = new ResponseBody("Mentee deleted successfully!", false, HttpStatus.OK, mentee);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/get_all_deleted/{username}")
    public ResponseEntity<ResponseBody> getAllDeletedMentees(@PathVariable String username) throws Exception {
        System.out.println("Inside get all deleted mentees: " + username);
        Iterable<Mentee> allDeletedMentees = menteeService.getAllDeletedByMenteeUsername(username);
        ResponseBody responseBody = new ResponseBody("All deleted mentees of: " + username, false, HttpStatus.OK, allDeletedMentees);
        return ResponseEntity.ok(responseBody);
    }
}

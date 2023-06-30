package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMenteeForMentor;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMentor;
import dev.abhinavreddy.guruva.reqres.ResponseBody;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {
    private final MentorService mentorService;
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/create") // ✅
    public ResponseEntity<ResponseBody> createMentor(@RequestBody CreateMentor requestBody){
        System.out.println("Inside create mentor: " + requestBody.getMentor());

        Mentor newMentor = mentorService.createMentor(requestBody.getMentor(), requestBody.getUsername());
        ResponseBody responseBody = new ResponseBody("Mentor created successfully!", false, org.springframework.http.HttpStatus.OK, newMentor);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/create_mentee_for_mentor") // ✅
    public ResponseEntity<Object> createMenteeForMentor(@RequestBody CreateMenteeForMentor requestBody) throws Exception {
        System.out.println("Inside create mentee for mentor: " + requestBody.getMentorId() + " " + requestBody.getMenteeUsername());
        Mentee newMentee = mentorService.createMenteeForMentor(requestBody.getMentorId(), requestBody.getMenteeUsername());
        ResponseBody responseBody = new ResponseBody("Mentee created successfully!", false, org.springframework.http.HttpStatus.OK, newMentee);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseBody> getMentor(@PathVariable ObjectId id) throws Exception {
        System.out.println("Inside get mentor: " + id);
        Mentor mentor = mentorService.getMentorById(id);
        ResponseBody responseBody = new ResponseBody("Mentor with id: " + id, false, org.springframework.http.HttpStatus.OK, mentor);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/get_all/{username}") // ✅
    public ResponseEntity<ResponseBody> getMentor(@PathVariable String username) throws Exception {
        System.out.println("Inside get mentors: " + username);
        Iterable<Mentor> allMentors = mentorService.getAllMentorsByMentorUsername(username);
        ResponseBody responseBody = new ResponseBody("All mentors of: " + username, false, org.springframework.http.HttpStatus.OK, allMentors);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/update_availability/{mentorId}") // ✅
    public ResponseEntity<ResponseBody> updateAvailability(@PathVariable ObjectId mentorId, @RequestBody Map<String, Boolean> availability) throws Exception {
        System.out.println("Inside update availability: " + mentorId + " " + availability.get("status"));
        Mentor mentor = mentorService.updateMentorAvailability(mentorId, availability.get("status"));
        ResponseBody responseBody = new ResponseBody("Availability updated successfully!", false, org.springframework.http.HttpStatus.OK, mentor);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("remove_mentor_for_mentee/{menteeId}") // ✅
    public ResponseEntity<ResponseBody> removeMentorForMentee(@PathVariable ObjectId menteeId) throws Exception {
        System.out.println("Inside remove mentee: " + menteeId);
        Boolean isRemoved = mentorService.removeMentorForMentee(menteeId);
        ResponseBody responseBody = new ResponseBody("Mentee removed successfully!", false, org.springframework.http.HttpStatus.OK, isRemoved);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/delete/{id}") // ✅
    public ResponseEntity<ResponseBody> deleteMentor(@PathVariable ObjectId id) throws Exception {
        System.out.println("Inside delete mentor: " + id);
        mentorService.deleteMentor(id);
        ResponseBody responseBody = new ResponseBody("Mentor deleted successfully!", false, org.springframework.http.HttpStatus.OK, null);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/get_all_deleted/{username}") // ✅
    public ResponseEntity<ResponseBody> getAllDeletedMentors(@PathVariable String username) throws Exception {
        System.out.println("Inside get all deleted mentors");
        Iterable<Mentor> allDeletedMentors = mentorService.getAllDeletedMentorsByMentorUsername(username);
        ResponseBody responseBody = new ResponseBody("All deleted mentors", false, org.springframework.http.HttpStatus.OK, allDeletedMentors);
        return ResponseEntity.ok(responseBody);
    }
}

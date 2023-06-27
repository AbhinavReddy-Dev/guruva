package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.reqbodytypes.CreateMentee;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMentorForMentee;
import dev.abhinavreddy.guruva.mentor.Mentor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mentee")

public class MenteeController {
    private final MenteeRepository menteeRepository;
    private final MenteeService menteeService;
    public MenteeController(MenteeRepository menteeRepository, MenteeService menteeService) {
        this.menteeRepository = menteeRepository;
        this.menteeService = menteeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Mentee> createMentee(@RequestBody CreateMentee requestBody){
        System.out.println("Inside create mentee: " + requestBody.getMentee());
        Mentee newMentee = menteeService.createMentee(requestBody.getMentee(), requestBody.getUsername());
        return ResponseEntity.ok(newMentee);
    }

    @PostMapping("/create_mentor_for_mentee")
    public ResponseEntity<Mentor> createMentorForMentee(@RequestBody CreateMentorForMentee requestBody){
        System.out.println("Inside create mentor for mentee: " + requestBody.getMenteeId() + " " + requestBody.getMentorUsername());
        Mentor newMentor = menteeService.createMentorForMentee(requestBody.getMenteeId(), requestBody.getMentorUsername());
        return ResponseEntity.ok(newMentor);
    }

}

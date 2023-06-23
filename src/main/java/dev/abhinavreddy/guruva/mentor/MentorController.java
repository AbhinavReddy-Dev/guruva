package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMenteeForMentor;
import dev.abhinavreddy.guruva.reqbodytypes.CreateMentor;
import dev.abhinavreddy.guruva.reqres.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {
    private final MentorRepository mentorRepository;
    private final MentorService mentorService;
    public MentorController(MentorRepository mentorRepository, MentorService mentorService) {
        this.mentorRepository = mentorRepository;
        this.mentorService = mentorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Mentor> createMentor(@RequestBody CreateMentor requestBody){


        System.out.println("Inside create mentor: " + requestBody.getMentor());


        Mentor newMentor = mentorService.createMentor(requestBody.getMentor(), requestBody.getUsername());
        return ResponseEntity.ok(newMentor);
    }

    @PostMapping("/create_mentee_for_mentor")
    public ResponseEntity<Object> createMenteeForMentor(@RequestBody CreateMenteeForMentor requestBody) throws Exception {
        System.out.println("Inside create mentee for mentor: " + requestBody.getMentorId() + " " + requestBody.getMenteeUsername());
//
        Mentee newMentee = mentorService.createMenteeForMentor(requestBody.getMentorId(), requestBody.getMenteeUsername());
        ResponseBody responseBody = new ResponseBody("Mentee created successfully!", false, org.springframework.http.HttpStatus.OK, newMentee);
        return ResponseEntity.ok(responseBody);
    }
}

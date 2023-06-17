package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.customtypes.*;
import dev.abhinavreddy.guruva.mentee.Mentee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Mentee> createMenteeForMentor(@RequestBody CreateMenteeForMentor requestBody){
        System.out.println("Inside create mentee for mentor: " + requestBody.getMentorId() + " " + requestBody.getMenteeUsername());
        Mentee newMentee = mentorService.createMenteeForMentor(requestBody.getMentorId(), requestBody.getMenteeUsername());
        return ResponseEntity.ok(newMentee);
    }
}

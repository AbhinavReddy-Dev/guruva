package dev.abhinavreddy.guruva.mentor;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Mentor> createMentor(Mentor mentor, String username){
        Mentor newMentor = mentorService.createMentor(mentor, username);
        return ResponseEntity.ok(newMentor);
    }
}

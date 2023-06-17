package dev.abhinavreddy.guruva.customtypes;

import dev.abhinavreddy.guruva.mentor.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMentor {
    private String username;
    private Mentor mentor;
}

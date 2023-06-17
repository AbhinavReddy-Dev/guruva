package dev.abhinavreddy.guruva.customtypes;

import dev.abhinavreddy.guruva.mentee.Mentee;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMentee {
    private String username;
    private Mentee mentee;

}

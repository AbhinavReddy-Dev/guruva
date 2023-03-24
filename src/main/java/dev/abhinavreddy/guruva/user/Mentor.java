package dev.abhinavreddy.guruva.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {
    String username;
    ArrayList<Skill> skills;
}

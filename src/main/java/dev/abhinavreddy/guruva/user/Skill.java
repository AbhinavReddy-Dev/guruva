package dev.abhinavreddy.guruva.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private String name;
    private SkillLevel level;
}

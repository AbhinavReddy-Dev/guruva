package dev.abhinavreddy.guruva.customtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private String name;
    private SkillLevel level;
//    private SkillType type;
}

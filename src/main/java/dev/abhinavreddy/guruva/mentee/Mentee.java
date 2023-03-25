package dev.abhinavreddy.guruva.mentee;

import dev.abhinavreddy.guruva.customtypes.LearningMode;
import dev.abhinavreddy.guruva.customtypes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mentee")
public class Mentee {
    @Id
    private ObjectId id;
    private String mentor;
    private String mentee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private List<Skill> skills;
    private LearningMode learningMode;
    private Integer rating;
    private Boolean isClosed;
}
// dummy data
// {
//     "id": "123",
//     "mentor": "abhinav",
//     "mentee": "abhinav",
//     "createdAt": "2020-10-10T10:10:10",
//     "updatedAt": "2020-10-10T10:10:10",
//     "closedAt": "2020-10-10T10:10:10",
//     "learningSkills": [
//         {
//             "name": "java",
//             "level": "beginner"
//         }
//     ],
//     "learningMode": "online",
//     "isClosed": false
// }


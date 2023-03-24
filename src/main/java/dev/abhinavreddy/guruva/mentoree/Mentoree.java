package dev.abhinavreddy.guruva.mentoree;

import dev.abhinavreddy.guruva.user.Mode;
import dev.abhinavreddy.guruva.user.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mentoree")
public class Mentoree {
    private ObjectId id;
    private String mentor;
    private String mentee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private ArrayList<Skill> learningSkills;
    private Mode learningMode;
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


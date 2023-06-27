package dev.abhinavreddy.guruva.mentee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.abhinavreddy.guruva.customtypes.LearningMode;
import dev.abhinavreddy.guruva.customtypes.Skill;
import dev.abhinavreddy.guruva.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mentee")
@JsonIgnoreProperties({"isDeleted"})
public class Mentee {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    @DocumentReference(db = "guruva", collection = "user")
    private User mentee;
    @DocumentReference(db = "guruva", collection = "user")
    private User mentor;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @NonNull
    private List<Skill> skills;
    @NonNull
    private LearningMode learningMode;
    private Boolean isOpen = true;
    private Boolean isDeleted = false;
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


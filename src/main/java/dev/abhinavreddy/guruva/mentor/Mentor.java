package dev.abhinavreddy.guruva.mentor;

import dev.abhinavreddy.guruva.customtypes.LearningMode;
import dev.abhinavreddy.guruva.customtypes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mentor")
public class Mentor {
    @Id
    private ObjectId id;
    @DocumentReference(db = "guruva", collection = "user")
    private String mentor;
    private List<Skill> skills;
    private LearningMode learningMode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer rating;
    private Boolean isAvailable;
}


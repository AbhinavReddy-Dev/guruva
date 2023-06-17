package dev.abhinavreddy.guruva.mentor;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.abhinavreddy.guruva.customtypes.LearningMode;
import dev.abhinavreddy.guruva.customtypes.Skill;
import dev.abhinavreddy.guruva.user.User;
import lombok.*;
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
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection = "mentor")
public class Mentor {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    @DocumentReference(db = "guruva", collection = "user")
    private User mentor;
    @NonNull
    private List<Skill> skills;
    @NonNull
    private LearningMode learningMode;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @NonNull
    private Boolean isAvailable;
    private Boolean isDeleted = false;
}


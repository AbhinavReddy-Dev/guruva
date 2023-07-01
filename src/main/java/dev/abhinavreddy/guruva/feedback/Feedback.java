package dev.abhinavreddy.guruva.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.abhinavreddy.guruva.customtypes.FeedbackType;
import dev.abhinavreddy.guruva.mentee.Mentee;
import dev.abhinavreddy.guruva.mentor.Mentor;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "feedback")
@JsonIgnoreProperties({"isDeleted"})
public class Feedback {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private FeedbackType type;
    @NonNull
    @DocumentReference(db = "guruva", collection = "user")
    private User byUser;
    @NonNull
    @DocumentReference(db = "guruva", collection = "user")
    private User forUser;
    @DocumentReference(db = "guruva", collection = "mentee")
    private Mentee mentee;
    @DocumentReference(db = "guruva", collection = "mentor")
    private Mentor mentor;
    private Float rating;
    private String comment;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean isDeleted = false;
}

// feedback type enum



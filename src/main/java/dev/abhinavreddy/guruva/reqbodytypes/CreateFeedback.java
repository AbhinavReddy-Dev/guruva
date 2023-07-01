package dev.abhinavreddy.guruva.reqbodytypes;

import dev.abhinavreddy.guruva.customtypes.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedback {
    private FeedbackType type;
    private String byUsername;
    private String forUsername;
    private ObjectId mentorId;
    private ObjectId menteeId;
    private Float rating;
    private String comment;
}

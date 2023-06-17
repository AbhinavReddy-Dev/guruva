package dev.abhinavreddy.guruva.customtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class CreateMenteeForMentor {
    private ObjectId mentorId;
    private String menteeUsername;
}

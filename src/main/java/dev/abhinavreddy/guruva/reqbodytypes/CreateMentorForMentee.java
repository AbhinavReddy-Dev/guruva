package dev.abhinavreddy.guruva.reqbodytypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class CreateMentorForMentee {
    private ObjectId menteeId;
    private String mentorUsername;
}

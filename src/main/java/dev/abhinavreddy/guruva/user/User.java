package dev.abhinavreddy.guruva.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.abhinavreddy.guruva.customtypes.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@Document(collection = "user")
public class User {
        @Id
        @JsonSerialize(using= ToStringSerializer.class)
        private ObjectId id;
        @NonNull
        private String username;
        @NonNull
        private String password;
        @NonNull
        private String fullName;
        @NonNull
        private Gender gender;
        @NonNull
        private String email;
        private String userToken;
        private String photo;
        private List<String> languagesSpoken;
        private String country;
        private UserMode usermode;
        private List<String> externalLinks;
        private List<Experience> experience;
        private List<Skill> skills;
        private List<Skill> skillsNeedHelp;
        private List<Skill> skillsGotHelp;
        private List<Skill> skillsCanHelp;
        private Integer mentorRating;
        private Integer menteeRating;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
}
//        dummy data in json format
//        {"_id":{"$oid":"641ceb6fda78acd08a0381df"},
//        "photo":"https://fastly.picsum.photos/id/488/200/200.jpg?hmac=V8mvdG1ON09kNw80-qS00BSFq5gGhqRYoYPJftrsYA8",
//        "username":"abhinavreddym",
//        "password": "password",
//        "userToken": "token",
//        "fullName": "Abhinav Reddy",
//        "gender": MALE,
//        "email": "blabla@gmail.com",
//        "languagesSpoken": ["English", "Hindi"],
//        "country": "India",
//        "mode": MENTOR,
//        "externalLinks": ["https://www.google.com", "https://www.facebook.com"],
//        "experience": [{ "company": "Google", "years": 2, "skillsUsed": ["Java", "Python"] }],
//        "skills": [{ "name": "Java", "level": NOVICE }, { "name": "Python", "level": INTERMEDIATE }],
//        "preferredMode": TEXT,
//        "skillsThatNeedHelp": [{ "name": "Java", "level": NOVICE }, { "name": "Python", "level": INTERMEDIATE }],
//        "skillsGotHelp": [{ "name": "Java", "level": NOVICE }, { "name": "Python", "level": INTERMEDIATE }],
//        "mentors": [{ "username": "abhinavreddy", "skills": [{ "name": "Java", "level": NOVICE }, { "name": "Python", "level": INTERMEDIATE }] }],
//        "mentorRating": 5,
//        "menteeRating": 5
//        }



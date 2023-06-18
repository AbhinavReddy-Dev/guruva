package dev.abhinavreddy.guruva.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.abhinavreddy.guruva.customtypes.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonIgnoreProperties({"password", "userToken"})
@Document(collection = "user")
public class User {
        @Id
        @JsonSerialize(using= ToStringSerializer.class)
        private ObjectId id;
        @NonNull
        @Indexed(unique = true)
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
        private List<String> externalLinks;
        private List<Experience> experience;
        private List<Skill> skills;
        private Float mentorRating;
        private Float menteeRating;
        @CreatedDate
        private LocalDateTime createdAt;
        @LastModifiedDate
        private LocalDateTime updatedAt;

// public method to return user details without password
//public Map<String, Object> getUserDetails() {
//
//        Map<String, Object> userDetails = new HashMap<>();
//
//        userDetails.put("id", this.id);
//        userDetails.put("username", this.username);
//        userDetails.put("fullName", this.fullName);
//        userDetails.put("gender", this.gender);
//        userDetails.put("email", this.email);
//        userDetails.put("userToken", this.userToken);
//        userDetails.put("photo", this.photo);
//        userDetails.put("languagesSpoken", this.languagesSpoken);
//        userDetails.put("country", this.country);
//        userDetails.put("externalLinks", this.externalLinks);
//        userDetails.put("experience", this.experience);
//        userDetails.put("skills", this.skills);
//        userDetails.put("mentorRating", this.mentorRating);
//        userDetails.put("menteeRating", this.menteeRating);
//        userDetails.put("createdAt", this.createdAt);
//        userDetails.put("updatedAt", this.updatedAt);
//
//        return userDetails;
//}


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



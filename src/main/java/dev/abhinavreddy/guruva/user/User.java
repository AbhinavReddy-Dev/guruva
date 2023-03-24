package dev.abhinavreddy.guruva.user;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {
        @Id
        private ObjectId id;
        private String photo;
        private String username;
        private String password;
        private String userToken;
        private String fullName;
        private Gender gender;
        private String email;
        private ArrayList<String> languagesSpoken;
        private String country;
        private UserMode mode;
        private ArrayList<ExternalLinks> externalLinks;
        private ArrayList<Experience> experience;
        private ArrayList<Skill> skills;
        private Mode preferredMode;
        private ArrayList<Skill> skillsThatNeedHelp;
        private ArrayList<Skill> skillsGotHelp;
        private ArrayList<Mentor> mentors;
        private Integer mentorRating;
        private Integer menteeRating;
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



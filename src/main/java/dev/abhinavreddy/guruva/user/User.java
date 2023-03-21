package dev.abhinavreddy.guruva.user;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
public record User(
        Integer  id,
        String photo,
        @Id
        String username,
        String fullName,
        Gender gender,
        String email,
        ArrayList<String> languagesSpoken,
        String country,
        UserMode mode,
        ArrayList<String> externalLinks,
        ArrayList<Experience> experience,
        ArrayList<Skill> skills,
        Mode preferredMode,
        ArrayList<Skill> skillsThatNeedHelp,
        ArrayList<Skill> skillsGotHelp,
        Integer mentorRating,
        Integer menteeRating
) { }
//
//    ID
//    Photo
//    Username - Unique
//    Full Name
//    Gender
//    Email - Unique
//    Languages Spoken
//    Country
//    Mode
//            (Mentor, Mentee)
//    External Links
//            (GitHub, LinkedIn, Portfolio, etc)
//    Experience
//            (Company, Years, Skills Used)
//    Skills
//            (Novice, Intermediate, Senior)
//    Preferred Mode
//            (Text, Video, Voice)
//    Skills That Need Help
//    Skills Got Helped
//    Mentor Rating
//    Mentee Rating


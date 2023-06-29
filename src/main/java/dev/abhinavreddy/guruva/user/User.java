package dev.abhinavreddy.guruva.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonIgnoreProperties({"userToken", "isDeleted"})
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        private Boolean isDeleted = false;
        private Boolean isPrivate = false;

}


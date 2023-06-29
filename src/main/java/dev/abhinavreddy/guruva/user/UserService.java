package dev.abhinavreddy.guruva.user;

import dev.abhinavreddy.guruva.exceptions.UserAlreadyExists;
import dev.abhinavreddy.guruva.exceptions.UserNotFound;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public User createUser(User user) throws Exception { // ✅
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new UserAlreadyExists("User already exists: " + user.getUsername());
            }
            String password = user.getPassword();
            if (verifyPasswordStrength(password)) {
                // TODO: new exception
                throw new Exception("Invalid password");
            }
            // hash password with base64 and save
            String hashedPassword = new String(java.util.Base64.getEncoder().encode(password.getBytes()));
            user.setPassword(hashedPassword);
            user.setUserToken(generateNewToken(user.getUsername()));
            user.setMenteeRating(0.0F);
            user.setMentorRating(0.0F);
            userRepository.save(user);
            return userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFound("User not found: " + user.getUsername()));
        }
        catch (UserAlreadyExists e) {
            throw e;
        }
        catch (Exception e) {
            throw new Exception("Error creating user. " + e.getLocalizedMessage());
        }
    }

    public User getUser(String username) throws UserNotFound { // ✅
        try{

            return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
        }
        catch (UserNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new UserNotFound("Error getting user. " + e.getLocalizedMessage());
        }
    }

    public User updateUser(User user) throws Exception { // ✅
        try {
//            System.out.println("Inside service: " + user);

            userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFound("User not found: " + user.getUsername()));

            Query query = new Query().addCriteria(Criteria.where("username").is(user.getUsername()));

            Update update = new Update();
            if (user.getFullName() != null)
                update.set("fullName", user.getFullName());
            if (user.getEmail() != null)
                update.set("email", user.getEmail());
            if(user.getGender() != null)
                update.set("gender", user.getGender());
            if (user.getPhoto() != null)
                update.set("photo", user.getPhoto());
            if (user.getLanguagesSpoken() != null)
                update.set("languagesSpoken", user.getLanguagesSpoken());
            if (user.getCountry() != null)
                update.set("country", user.getCountry());
            if (user.getExperience() != null)
                update.set("experience", user.getExperience());
            if (user.getSkills() != null)
                update.set("skills", user.getSkills());

            mongoTemplate.updateFirst(query, update, User.class);
            return userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFound("User not found: " + user.getUsername()));
        }
        catch (UserNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new Exception("Error updating user. "+ e.getLocalizedMessage());
        }
    }

//    update username throwing exception if username already exists
    public User updateUsername(String username, String newUsername) throws Exception { // ✅
        try {
            if (userRepository.findByUsername(newUsername).isPresent()) {
                throw new UserAlreadyExists("User already exists: " + newUsername);
            }
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
            String token = generateNewToken(newUsername);

            Query query = new Query().addCriteria(Criteria.where("username").is(user.getUsername()));
            Update update = new Update();
            update.set("username", newUsername);
            update.set("userToken", token);
            mongoTemplate.updateFirst(query, update, User.class);

            return userRepository.findByUsername(newUsername).orElseThrow(() -> new UserNotFound("User not found: " + newUsername));
        }
        catch (UserAlreadyExists | UserNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Error updating username");
        }
    }

    public void updatePassword(String username, String password) throws Exception {
        try {
            User userPass = userRepository.findPasswordByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
            // throws exception if password is shorter than 8 characters or longer than 20 characters or does not contain a number or does not contain a special character or does not contain an uppercase letter or does not contain a lowercase letter or is the same as the old password
//            decrypt password
            String oldPassword = new String(java.util.Base64.getDecoder().decode(userPass.getPassword().getBytes()));

            if (oldPassword.equals(password)) {
                throw new Exception("New password cannot be the same as the old password.");
            }
            if (verifyPasswordStrength(password)) {
            // TODO: new exception
                throw new Exception("Invalid password.");
            }

            // hash password with base64 and save
            String hashedPassword = new String(java.util.Base64.getEncoder().encode(password.getBytes()));

            Query query = new Query().addCriteria(Criteria.where("username").is(username));
            Update update = new Update();
            update.set("password", hashedPassword);
            mongoTemplate.updateFirst(query, update, User.class);
        }
        catch (UserNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public Optional<User> updateMentorRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMentorRating(user.getMentorRating());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateMenteeRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMenteeRating(user.getMenteeRating());
            return userRepository.save(u);
        });
    }

    public Boolean deleteUser(String username) throws UserNotFound {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
            Query query = new Query().addCriteria(Criteria.where("username").is(username));
            Update update = new Update();
            update.set("isDeleted", true);
            mongoTemplate.updateFirst(query, update, User.class);
            return true;
        }
        catch (UserNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new UserNotFound("Error deleting user. " + e.getLocalizedMessage());
        }
    }

    public String generateNewToken(String username) { // ✅
        StringBuilder token = new StringBuilder();
        for(int i = 0; i < username.length(); i++) {
            token.append(username.charAt(i));
            token.append((char) (Math.random() * 26 + 'a'));
        }
        return token.toString();
    }

    public Boolean verifyPasswordStrength(String password){ // ✅
        return password.length() < 8 || password.length() > 20 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*");
    }
}
